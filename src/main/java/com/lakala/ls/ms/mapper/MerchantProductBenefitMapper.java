package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.MPBenefitRule;

@Mapper
public interface MerchantProductBenefitMapper {
	
	
	
	public int addMPBenefitRules(List<MPBenefitRule> list );
	public List<MPBenefitRule> queryMerchantBenefits(long prdid,long merchantId);
	
	public int deleteMPBenefit(	 long productId, long merchantId);
	
	public int deleteMPBenefitRules(long productId, long merchantId);
	


}
