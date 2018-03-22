package com.lakala.ls.ms.mapper;

import java.util.List;
import java.util.Map;

import com.lakala.ls.ms.dto.ChannelShareBenefitDTO;
import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.dto.CheckLoanApplyDTO;


@Mapper
public interface ChannelShareBenefitMapper {

    public List<ChannelShareBenefitDTO> queryShareBenefit();

}