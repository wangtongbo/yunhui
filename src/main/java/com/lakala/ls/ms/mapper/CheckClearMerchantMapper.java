package com.lakala.ls.ms.mapper;

import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CheckClearMerchantMapper {

    public Integer saveCheckClearMerchantList(Map map);

    public List<CheckLoanApplyDTO> queryCheckClearMerchantList(UserLoanApply userLoanApply);

    public int queryCheckClearMerchantCount(UserLoanApply userLoanApply);

}