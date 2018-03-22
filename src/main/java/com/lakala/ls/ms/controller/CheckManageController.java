package com.lakala.ls.ms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Channel;
import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.dto.UserLoanApplyDTO;
import com.lakala.ls.ms.service.ChannelService;
import com.lakala.ls.ms.service.LoanApplyService;
import com.lakala.ls.ms.service.SyncLoanRecordExcelService;

/**
 * 后管对账模块业务处理
 * Created by wenhemin on 2017/6/13.
 */
@RestController
@RequestMapping(value = "/check")
public class CheckManageController {

    @Autowired
    private LoanApplyService loanApplyService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private SyncLoanRecordExcelService syncLoanRecordExcelService;

    /**
     * 查询用户贷款记录列表
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ROLE_CHECK')")
    @RequestMapping(value = "/listUserLoanRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map listUserLoanRecord(@RequestBody UserLoanApply request) {
        Map resultMap = new HashMap<>();

        if(request.getPageSize() != null) {
            request.setPageIndex(request.getPageSize() * (request.getPageNum() - 1));
        }
        List<UserLoanApplyDTO> list = loanApplyService.listUserLoanRecord(request);
        resultMap.put("loanList", list);

        int count = loanApplyService.listUserLoanRecordCount(request);
        resultMap.put("count", count);

        return resultMap;
    }

    /**
     * 贷款记录Excel入库
     * @param excelFile
     * @param merchantId
     * @param productId
     * @return
     * @throws LsException
     */
    @PreAuthorize("hasRole('ROLE_CHECK')")
    @RequestMapping(value = "/uploadLoanAppayRecord", method = RequestMethod.POST)
    @ResponseBody
    public Integer uploadLoanAppayRecord(@RequestParam("excelFile") MultipartFile excelFile,
                                         @RequestParam("merchantId") Long merchantId,
                                         @RequestParam("productId") Long productId) throws LsException {
        UserLoanApply request = new UserLoanApply();
        request.setMerchantId(merchantId);
        request.setProductId(productId);
        request.setExcelFile(excelFile);
        return syncLoanRecordExcelService.doSyncLoanRecord(request);
    }

    /**
     * 查询供货商对账记录
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ROLE_CHECK')")
    @RequestMapping(value = "/listMerchantCheckRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map listMerchantCheckRecord(@RequestBody UserLoanApply request) {
        Map resultMap = new HashMap<>();

        if(request.getPageSize() != null) {
            request.setPageIndex(request.getPageSize() * (request.getPageNum() - 1));
        }

        List<CheckLoanApplyDTO> list = loanApplyService.listMerchantLoanRecord(request);
        resultMap.put("loanList", list);

        int count = loanApplyService.listMerchantLoanRecordCount(request);
        resultMap.put("count", count);

        return resultMap;
    }

    /**
     * 查询渠道对账记录
     * @param request
     * @return
     */
    @PreAuthorize("hasRole('ROLE_CHECK')")
    @RequestMapping(value = "/listChannelCheckRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map listChannelCheckRecord(@RequestBody UserLoanApply request) {
        Map resultMap = new HashMap<>();

        if(request.getPageSize() != null) {
            request.setPageIndex(request.getPageSize() * (request.getPageNum() - 1));
        }

        List<CheckLoanApplyDTO> list = loanApplyService.listChannelLoanRecord(request);
        resultMap.put("loanList", list);

        int count = loanApplyService.listChannelLoanRecordCount(request);
        resultMap.put("count", count);

        return resultMap;
    }

    /**
     * 查询供货商产品关系列表
     * @return
     */
    @RequestMapping(value = "/listMerchantProduct", method = RequestMethod.GET)
    @ResponseBody
    public List<MerchantDTO> listMerchantProduct() {
        return loanApplyService.queryMerchantProduct();
    }

    /**
     * 查询渠道列表
     * @return
     */
    @RequestMapping(value = "/listChannel", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> listChannel() {
        return channelService.queryChannels();
    }

}
