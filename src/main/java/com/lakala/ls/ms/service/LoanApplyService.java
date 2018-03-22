package com.lakala.ls.ms.service;

import java.util.List;

import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import com.lakala.ls.ms.mapper.CheckClearChannelMapper;
import com.lakala.ls.ms.mapper.CheckClearMerchantMapper;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.dto.UserLoanApplyDTO;
import com.lakala.ls.ms.mapper.UserLoanApplyMapper;


/**
 * Created by whmyy on 17/5/10.
 */
@Service(value = "loanApplyService")
public class LoanApplyService {

    @Autowired
    private UserLoanApplyMapper loanApplyMapper;

    @Autowired
    private CheckClearChannelMapper checkClearChannelMapper;

    @Autowired
    private CheckClearMerchantMapper checkClearMerchantMapper;

    public List<UserLoanApplyDTO> listUserLoanRecord(UserLoanApply request) {
        return loanApplyMapper.queryUserLoanApplyList(request);
    }

    public int listUserLoanRecordCount(UserLoanApply request) {
        return loanApplyMapper.queryUserLoanApplyCount(request);
    }

    public List<CheckLoanApplyDTO> listMerchantLoanRecord(UserLoanApply request) {
        return checkClearMerchantMapper.queryCheckClearMerchantList(request);
    }

    public int listMerchantLoanRecordCount(UserLoanApply request) {
        return checkClearMerchantMapper.queryCheckClearMerchantCount(request);
    }

    public List<CheckLoanApplyDTO> listChannelLoanRecord(UserLoanApply request) {
        return checkClearChannelMapper.queryCheckClearChannelList(request);
    }

    public int listChannelLoanRecordCount(UserLoanApply request) {
        return checkClearChannelMapper.queryCheckClearChannelCount(request);
    }

    public List<MerchantDTO> queryMerchantProduct() {
        return loanApplyMapper.queryMerchantProduct();
    }
}
