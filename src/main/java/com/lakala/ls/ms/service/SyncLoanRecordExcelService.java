package com.lakala.ls.ms.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lakala.ls.CONMSG;
import com.lakala.ls.ErrorMSG;
import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.UserLoanApplyDTO;
import com.lakala.ls.ms.mapper.UserLoanApplyMapper;
import com.lakala.ls.ms.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入贷款记录服务(Excel)
 * @author wenhemin
 * @since 2017-06-20
 */
@Service(value = "syncLoanRecordExcelService")
public class SyncLoanRecordExcelService {

    private final static String LOAN_ID_SYMBOL = "_";

    private final static String EXCEL_LOAN_STATE_1 = "贷款申请中";
    private final static String EXCEL_LOAN_STATE_2 = "贷款成功";
    private final static String EXCEL_LOAN_STATE_3 = "申请失败";

    private final static String EXCEL_PHASED_UNIT_1 = "日";
    private final static String EXCEL_PHASED_UNIT_2 = "月";
    private final static String EXCEL_PHASED_UNIT_3 = "年";

    @Autowired
    private UserLoanApplyMapper loanApplyMapper;

    /**
     * 执行同步操作
     * @param request
     * @return
     * @throws LsException
     */
    public Integer doSyncLoanRecord(UserLoanApply request) throws LsException {
        List<UserLoanApplyDTO> loanList = new ArrayList<>();

        MultipartFile excelFile = request.getExcelFile();

        // 输入流
        InputStream fis = null;

        // 创建Excel工作薄
        XSSFWorkbook hwb = null;

        try {
            String platform = excelFile.getOriginalFilename().split("\\.")[1];
            if (!"xlsx".equals(platform)) {
                // 文件格式错误
                throw new LsException(ErrorMSG.LS2002, "Excel格式只支持xlsx!");
            }

            fis = excelFile.getInputStream();
            hwb = new XSSFWorkbook(fis);

            // 解析excel数据
            loanList = resolveData(hwb, request);
        } catch (Exception e) {
            e.printStackTrace();

            if(e instanceof LsException) {
                throw (LsException) e;
            } else {
                throw new LsException(ErrorMSG.LS2002, "导入贷款记录失败!");
            }
        }

        if(loanList.size() == 0) {
            return 0;
        }

        return saveUserLoanApplyList(loanList);
    }

    private List<UserLoanApplyDTO> resolveData(XSSFWorkbook hwb, UserLoanApply request) throws LsException{
        List<UserLoanApplyDTO> loanList = new ArrayList<>();

        String num = "0";

        try {
            // 得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;

            // 临时条件Map
            Map conditionMap = new HashMap<>();

            // 遍历该行所有的行,j表示行数 ,getPhysicalNumberOfRows行的总数
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    break;
                }

                UserLoanApplyDTO bean = new UserLoanApplyDTO();

                // 序号
                num = row.getCell(0) == null ? "" : row.getCell(0)
                        .getRawValue().trim();

                // 用户ID/手机号非空校验
                if(checkCellNull(row.getCell(3)) && checkCellNull(row.getCell(4))) {
                    throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:手机号或用户ID不能为空");
                }

                // 用户ID
                if(!checkCellNull(row.getCell(3))) {
                    String userId = row.getCell(3).getRawValue().trim();
                    conditionMap.put("userId", userId);
                    Map userMap = loanApplyMapper.queryUserInfo(conditionMap);

                    if(userMap == null || userMap.isEmpty()) {
                        throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:用户ID在系统中不存在");
                    }

                    bean.setUserId(userId);
                }

                // 手机号
                if(!checkCellNull(row.getCell(4))) {
                    String mobile = row.getCell(4).getRawValue().trim();
                    conditionMap.clear();
                    conditionMap.put("mobile", mobile);
                    Map userMap = loanApplyMapper.queryUserInfo(conditionMap);

                    if(userMap == null || userMap.isEmpty()) {
                        throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:用户手机号在系统中不存在");
                    }

                    bean.setMobile(mobile);
                    bean.setUserId((String) userMap.get("userId"));
                }

                // 供货商用户ID
                if(!checkCellNull(row.getCell(5))) {
                    bean.setMerchantUserId(row.getCell(5).getRawValue().trim());
                }

                // 用户贷款状态
                if(checkCellNull(row.getCell(7))) {
                    throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:贷款状态不能为空");
                } else {
                    String state = row.getCell(7).toString().trim();
                    if (EXCEL_LOAN_STATE_1.equals(state)) {
                        state = "1";
                    } else if (EXCEL_LOAN_STATE_2.equals(state)) {
                        state = "2";
                    } else if (EXCEL_LOAN_STATE_3.equals(state)) {
                        state = "3";
                    } else {
                        throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:贷款状态字典值错误");
                    }

                    bean.setState(state);
                }

                // 拒绝原因
                if(!checkCellNull(row.getCell(8))) {
                    String refuseReason = row.getCell(8).toString().trim();
                    bean.setRefuseReason(refuseReason);
                }

                // 贷款金额（元）
                if(!checkCellNull(row.getCell(9))) {
                    bean.setLoanAmount(new BigDecimal(row.getCell(9).toString().trim()));
                }

                // 分期单位
                if(!checkCellNull(row.getCell(10))) {
                    String phasedRepayUnit = row.getCell(10).toString().trim();
                    if (EXCEL_PHASED_UNIT_1.equals(phasedRepayUnit)) {
                        phasedRepayUnit = "1";
                    } else if (EXCEL_PHASED_UNIT_2.equals(phasedRepayUnit)) {
                        phasedRepayUnit = "2";
                    } else if (EXCEL_PHASED_UNIT_3.equals(phasedRepayUnit)) {
                        phasedRepayUnit = "3";
                    } else {
                        throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:分期时间单位字典值错误");
                    }
                    bean.setPhasedRepayUnit(phasedRepayUnit);
                }

                // 分期期数
                if(!checkCellNull(row.getCell(11))) {
                    String phasedRepayPeriod = row.getCell(11).toString().trim();
                    bean.setPhasedRepayPeriod((new Double(phasedRepayPeriod)).intValue());
                }

                // 还款利息
                if(!checkCellNull(row.getCell(12))) {
                    String repayInterest = row.getCell(12).toString().trim();
                    bean.setRepayInterest(new BigDecimal(repayInterest));
                }

                // 用户注册日期时间
                if(!checkCellNull(row.getCell(13))) {
                    Date merchantUserRegisterTime = row.getCell(13).getDateCellValue();
                    bean.setMerchantUserRegisterTime(DateUtil.getDateStr(merchantUserRegisterTime, DateUtil.format4));
                }

                // 用户申请日期时间
                Date applyTime = null;
                if(checkCellNull(row.getCell(14))) {
                    throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:申请时间不能为空");
                } else {
                    applyTime = row.getCell(14).getDateCellValue();
                    bean.setApplyTime(DateUtil.getDateStr(applyTime, DateUtil.format4));
                }

                // 用户批核日期时间
                Date approveTime = null;
                if(!checkCellNull(row.getCell(15))) {
                    approveTime = row.getCell(15).getDateCellValue();
                    bean.setApproveTime(DateUtil.getDateStr(approveTime, DateUtil.format4));
                }

                // 用户放款日期时间
                Date loanTime = null;
                if(!checkCellNull(row.getCell(16))) {
                    loanTime = row.getCell(16).getDateCellValue();
                    bean.setLoanTime(DateUtil.getDateStr(loanTime, DateUtil.format4));
                }

                String billId = null;
                if(!checkCellNull(row.getCell(6))) {
                    billId = row.getCell(6).getStringCellValue().trim(); // 拉卡拉用户订单ID
                } else {
                    if(applyTime != null) {
                        billId = bean.getUserId() + LOAN_ID_SYMBOL + DateUtil.getDateStrFormat(applyTime, DateUtil.format3);
                    } else if(approveTime != null) {
                        billId = bean.getUserId() + LOAN_ID_SYMBOL + DateUtil.getDateStrFormat(approveTime, DateUtil.format3);
                    } else if(loanTime != null) {
                        billId = bean.getUserId() + LOAN_ID_SYMBOL + DateUtil.getDateStrFormat(loanTime, DateUtil.format3);
                    } else {
                        throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行:申请,批核,放款时间不能都为空");
                    }
                }
                bean.setLoanNo(billId);

                bean.setProductId(request.getProductId());
                bean.setMerchantId(request.getMerchantId());
                loanList.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();

            if(e instanceof LsException) {
                throw (LsException) e;
            } else {
                throw new LsException(ErrorMSG.LS2003, "第" + Integer.parseInt(num) + "行数据解析失败!");
            }
        }

        return loanList;
    }

    private boolean checkCellNull(XSSFCell cell) {
        if(cell == null || "".equals(cell) || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return true;
        }

        return false;
    }

    private int saveUserLoanApplyList(List<UserLoanApplyDTO> loanList) {
        // 保存主表信息
        Map<String, Object> map = new HashMap<>();
        map.put("loanList", loanList);
        return loanApplyMapper.saveUserLoanApplyList(map);
    }

}
