package com.lakala.ls.ms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lakala.ls.CONMSG;
import com.lakala.ls.ms.domain.MPBenefitRule;
import com.lakala.ls.ms.dto.ChannelShareBenefitDTO;
import com.lakala.ls.ms.dto.CheckBatchDTO;
import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import com.lakala.ls.ms.dto.MerchantShareBenefitDTO;
import com.lakala.ls.ms.mapper.ChannelShareBenefitMapper;
import com.lakala.ls.ms.mapper.CheckBatchMapper;
import com.lakala.ls.ms.mapper.CheckClearChannelMapper;
import com.lakala.ls.ms.mapper.CheckClearMerchantMapper;
import com.lakala.ls.ms.mapper.CheckLoanApplyMapper;
import com.lakala.ls.ms.mapper.MerchantProductBenefitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 对账处理
 * Created by whmyy on 17/6/29.
 */
@Service(value = "checkClearService")
public class CheckClearService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckBatchMapper checkBatchMapper;

    @Autowired
    private CheckLoanApplyMapper checkLoanApplyMapper;

    @Autowired
    private CheckClearMerchantMapper checkClearMerchantMapper;

    @Autowired
    private CheckClearChannelMapper checkClearChannelMapper;

    @Autowired
    private MerchantProductBenefitMapper merchantProductBenefitMapper;

    @Autowired
    private ChannelShareBenefitMapper channelShareBenefitMapper;

//    @Autowired
//    private CheckBatchNo detailNo;

    public void check(String clearDate) {
        logger.info("===================初始化对账批次=====================");
        CheckBatchDTO checkBatchDTO = new CheckBatchDTO();
        checkBatchDTO.setClearDate(clearDate);
        checkBatchMapper.deleteCheckBatch(checkBatchDTO);

//        checkBatchDTO.setBatchId((String) detailNo.create());
        checkBatchDTO.setState(CONMSG.BATCH_STATE_0);
        checkBatchMapper.insertCheckBatch(checkBatchDTO);

        try {
            // 加载对账数据
            List<CheckLoanApplyDTO> checkList = loadCheckLoadRecord(clearDate);

            // 供货商对账
            merchatCheck(checkList);

            // 渠道对账
            channelCheck(checkList);

            checkBatchDTO.setState(CONMSG.BATCH_STATE_1);
        } catch(Exception e) {
            e.printStackTrace();
            checkBatchDTO.setState(CONMSG.BATCH_STATE_2);
        } finally {
            checkBatchMapper.updateCheckBatch(checkBatchDTO);
        }
    }

    private List<CheckLoanApplyDTO> loadCheckLoadRecord(String clearDate) {
        logger.info("===================清除当天对账记录数据=====================");
        checkLoanApplyMapper.deleteCheckLoanApply(clearDate);

        logger.info("===================加载贷款记录数据到对账记录表=====================");
        Map loadMap = new HashMap<>();
        loadMap.put("clearDate", clearDate);
        loadMap.put("state", CONMSG.LOAN_RECORD_STATE_2);
        checkLoanApplyMapper.loadCheckLoanApply(loadMap);

        return checkLoanApplyMapper.queryCheckLoanApply(clearDate);
    }

    private void merchatCheck(List<CheckLoanApplyDTO> checkList) {
        logger.info("===================供货商对账开始=====================");
        List<CheckLoanApplyDTO> checkClearList = new ArrayList<>();

        // 计算供货商分润金额
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        for(CheckLoanApplyDTO dto : checkList) {
            // 查询供货商分润规则
            rate = getMerchantDayShareBenefitRate(dto);

            if(rate == null) {
                dto.setPhaseState(CONMSG.PHASE_STATE_2);
                checkLoanApplyMapper.updateCheckLoanApplyState(dto);
                continue;
            }

            amount = dto.getLoanAmount().multiply(rate.divide(new BigDecimal(100)));
            dto.setMerchantShareBenefitRate(rate);
            dto.setMerchantShareBenefitAmount(amount);

            dto.setPhaseState(CONMSG.PHASE_STATE_1);
            dto.setPhase(CONMSG.PHASE_1);

            checkClearList.add(dto);

            // 更新对账记录状态
            checkLoanApplyMapper.updateCheckLoanApplyState(dto);
        }

        // 对账结果入库
        int count = 0;
        if(!checkClearList.isEmpty()) {
            Map checkMap = new HashMap<>();
            checkMap.put("checkClearList", checkClearList);
            count = checkClearMerchantMapper.saveCheckClearMerchantList(checkMap);
        }
        logger.info("===================供货商对账完成:对账数据[{}]条,入库[{}]条=====================",
                checkList.size(), count);
    }

    private BigDecimal getMerchantDayShareBenefitRate(final CheckLoanApplyDTO dto) {
        List<MPBenefitRule> ruleList = merchantProductBenefitMapper.queryMerchantBenefits(
                dto.getProductId(), dto.getMerchantId());

        if(ruleList == null || ruleList.isEmpty()) {
            return null;
        }

        if(CONMSG.FIRST_SUCCESS_LOAN_FLAG_1.equals(dto.getFirstSuccessLoanFlag())) {
            if(ruleList.size() == 1) {
                return ruleList.get(0).getFirstRate();
            } else {
                // TODO
            }
        } else {
            if(ruleList.size() == 1) {
                return ruleList.get(0).getRate();
            } else {
                // TODO
            }
        }

        return null;
    }

    private void channelCheck(List<CheckLoanApplyDTO> checkList) {
        logger.info("===================渠道对账开始=====================");
        List<CheckLoanApplyDTO> checkClearList = new ArrayList<>();

        // 渠道分润规则
        Map<String, BigDecimal> ruleMap = new HashMap<>();

        // K:子渠道,V:父渠道
        Map<String, Long> channelMap = new HashMap<>();

        // 查询渠道分润规则
        List<ChannelShareBenefitDTO> ruleList = channelShareBenefitMapper.queryShareBenefit();

        ruleList.forEach(dto -> {
            ruleMap.put(dto.getChannelId().toString() + dto.getProductId().toString(), dto.getRate());
            if(dto.getChildChannelId() != null && !"".equals(dto.getChildChannelId())) {
                channelMap.put(dto.getChildChannelId().toString(), dto.getChannelId());
            }
        });

        // 计算供货商分润金额
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        for(CheckLoanApplyDTO dto : checkList) {
            // 校验当前渠道是否是第一笔贷款,只有第一笔贷款进行分润处理
            if(checkClearChannelMapper.checkUserLoanByAgain(dto) == null) {
                dto.setPhaseState(CONMSG.PHASE_STATE_4);
                checkLoanApplyMapper.updateCheckLoanApplyState(dto);
                continue;
            }

            String ruleKey;
            if(channelMap.containsKey(dto.getChannelId().toString())) {
                // 这笔贷款渠道是子渠道
                ruleKey = channelMap.get(dto.getChannelId()).toString() + dto.getProductId();
                dto.setShareBenefitChannelId(channelMap.get(dto.getChannelId().toString()));
            } else {
                // 这笔贷款渠道是父渠道
                ruleKey = dto.getChannelId().toString() + dto.getProductId().toString();
                dto.setShareBenefitChannelId(dto.getChannelId());
            }

            if(!ruleMap.containsKey(ruleKey)) {
                dto.setPhaseState(CONMSG.PHASE_STATE_3);
                checkLoanApplyMapper.updateCheckLoanApplyState(dto);
                continue;
            }

            rate = ruleMap.get(ruleKey);
            amount = dto.getLoanAmount().multiply(rate.divide(new BigDecimal(100)));
            dto.setChannelShareBenefitRate(rate);
            dto.setChannelShareBenefitAmount(amount);

            dto.setPhaseState(CONMSG.PHASE_STATE_1);
            dto.setPhase(CONMSG.PHASE_2);

            checkClearList.add(dto);

            // 更新对账记录状态
            checkLoanApplyMapper.updateCheckLoanApplyState(dto);
        }

        // 对账结果入库
        int count = 0;
        if(!checkClearList.isEmpty()) {
            Map checkMap = new HashMap<>();
            checkMap.put("checkClearList", checkClearList);
            count = checkClearChannelMapper.saveCheckClearChannelList(checkMap);
        }
        logger.info("===================供货商对账完成:对账数据[{}]条,入库[{}]条=====================",
                checkList.size(), count);
    }
}
