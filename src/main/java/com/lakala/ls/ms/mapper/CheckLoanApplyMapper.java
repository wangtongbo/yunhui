package com.lakala.ls.ms.mapper;

import com.lakala.ls.ms.dto.CheckLoanApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CheckLoanApplyMapper {

    public Integer deleteCheckLoanApply(String clearDate);

    public Integer loadCheckLoanApply(Map map);

    public List<CheckLoanApplyDTO> queryCheckLoanApply(String clearDate);

    public Integer updateCheckLoanApplyState(CheckLoanApplyDTO dto);

}