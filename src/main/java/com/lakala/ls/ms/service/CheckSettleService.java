package com.lakala.ls.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.dto.CheckRepotDayDTO;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.mapper.CheckReportDayMapper;
import com.lakala.ls.ms.mapper.UserLoanApplyMapper;


/**
 * 对账处理
 * Created by whmyy on 17/6/29.
 */
@Service(value = "checkSettleService")
public class CheckSettleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckReportDayMapper checkReportDayMapper;

    @Autowired
    private UserLoanApplyMapper userLoanApplyMapper;

    public void settleByEveryday(String clearDate) {
        List<CheckRepotDayDTO> reportDayList = new ArrayList<>();

        // 查询产品列表
        List<MerchantDTO> merchantList = userLoanApplyMapper.queryMerchantProduct();

        // 统计各产品订单总数
        List<CheckRepotDayDTO> list1 = checkReportDayMapper.countLoanBillNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map1 = list1.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品用户总数
        List<CheckRepotDayDTO> list2 = checkReportDayMapper.countLoanUserNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map2 = list2.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品新增放款订单总数
        List<CheckRepotDayDTO> list3 = checkReportDayMapper.countNewLoanSuccessNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map3 = list3.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品再次放款订单总数
        List<CheckRepotDayDTO> list4 = checkReportDayMapper.countAgainLoanSuccessNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map4 = list4.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品新增放款用户数
        List<CheckRepotDayDTO> list5 = checkReportDayMapper.countNewUserSuccessNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map5 = list5.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品再次放款用户数
        List<CheckRepotDayDTO> list6 = checkReportDayMapper.countAgainUserSuccessNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map6 = list6.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品新增贷款总额
        List<CheckRepotDayDTO> list7 = checkReportDayMapper.countNewLoanSuccessAmountNum(clearDate);
        Map<Long, CheckRepotDayDTO> map7 = list7.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品再次贷款总额
        List<CheckRepotDayDTO> list8 = checkReportDayMapper.countAgainLoanSuccessAmountNum(clearDate);
        Map<Long, CheckRepotDayDTO> map8 = list8.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品新增贷款收益数
        List<CheckRepotDayDTO> list9 = checkReportDayMapper.countNewLoanProfitNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map9 = list9.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        // 统计各产品再次贷款收益数
        List<CheckRepotDayDTO> list10 = checkReportDayMapper.countAgainLoanProfitNumByProduct(clearDate);
        Map<Long, CheckRepotDayDTO> map10 = list10.stream().collect(Collectors.toMap(
                CheckRepotDayDTO::getProductId, (k) -> k));

        for(MerchantDTO dto : merchantList) {
            CheckRepotDayDTO resultDto = new CheckRepotDayDTO();
            resultDto.setProductId(dto.getProductId());
            resultDto.setDayLoanBillNum(map1.get(dto.getProductId()).getDayLoanBillNum());
            resultDto.setDayLoanUserNum(map2.get(dto.getProductId()).getDayLoanUserNum());
            resultDto.setNewLoanSuccessBillNum(map3.get(dto.getProductId()).getNewLoanSuccessBillNum());
            resultDto.setAgainLoanSuccessBillNum(map4.get(dto.getProductId()).getAgainLoanSuccessBillNum());
            resultDto.setNewLoanSuccessUserNum(map5.get(dto.getProductId()).getNewLoanSuccessUserNum());
            resultDto.setAgainLoanSuccessUserNum(map6.get(dto.getProductId()).getAgainLoanSuccessUserNum());
            resultDto.setNewLoanSuccessAmountNum(map7.get(dto.getProductId()).getNewLoanSuccessAmountNum());
            resultDto.setAgainLoanSuccessAmountNum(map8.get(dto.getProductId()).getAgainLoanSuccessAmountNum());
            resultDto.setNewShareBenefitAmountNum(map9.get(dto.getProductId()).getNewShareBenefitAmountNum());
            resultDto.setAgainShareBenefitAmountNum(map10.get(dto.getProductId()).getAgainShareBenefitAmountNum());
            reportDayList.add(resultDto);
        }

        checkReportDayMapper.saveCheckReportDayList(reportDayList);
    }
}
