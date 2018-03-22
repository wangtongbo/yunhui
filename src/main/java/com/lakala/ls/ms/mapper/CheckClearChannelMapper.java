package com.lakala.ls.ms.mapper;

import com.lakala.ls.ms.domain.UserLoanApply;
import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import com.lakala.ls.ms.dto.UserLoanApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CheckClearChannelMapper {

    public CheckLoanApplyDTO checkUserLoanByAgain(CheckLoanApplyDTO request);

    public Integer saveCheckClearChannelList(Map map);

    public List<CheckLoanApplyDTO> queryCheckClearChannelList(UserLoanApply userLoanApply);

    public int queryCheckClearChannelCount(UserLoanApply userLoanApply);

}