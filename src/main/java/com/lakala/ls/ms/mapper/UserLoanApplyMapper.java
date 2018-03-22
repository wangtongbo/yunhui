package com.lakala.ls.ms.mapper;

import java.util.List;
import java.util.Map;

import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.dto.UserLoanApplyDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserLoanApplyMapper {

    public List<UserLoanApplyDTO> queryUserLoanApplyList(UserLoanApply request);

    public int queryUserLoanApplyCount(UserLoanApply request);

    public Integer saveUserLoanApplyList(Map map);

    public List<MerchantDTO> queryMerchantProduct();

    public Map queryUserInfo(Map map);
    
    public Integer saveUserLoanApply(UserLoanApply userloan);
}