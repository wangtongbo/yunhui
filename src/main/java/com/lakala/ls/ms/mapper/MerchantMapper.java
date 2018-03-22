package com.lakala.ls.ms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.dto.MerchantDTO;

@Mapper
public interface MerchantMapper {

	public int saveMerchant(MerchantDTO dto);
	
	public List<MerchantDTO> queryMerchantList(Map map);
	
	public int updateMerchantState(MerchantDTO dto);
	
	public int updateMerchant(MerchantDTO dto);
	
}
