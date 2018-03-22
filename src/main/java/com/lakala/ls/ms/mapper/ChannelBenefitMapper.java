package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.ChannelBenefit;


@Mapper
public interface ChannelBenefitMapper {
	
	public int addChannelBenefit(ChannelBenefit cb);
	
	public List<ChannelBenefit> queryChannelBenefitByChannelId(long channelid);	
	
	public int deleteChannelBenefit(long channelid);

}
