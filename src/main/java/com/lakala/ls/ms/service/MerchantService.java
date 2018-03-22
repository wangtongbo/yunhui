package com.lakala.ls.ms.service;

import java.util.List;
import java.util.Map;

import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.mapper.MerchantMapper;
import com.lakala.ls.ms.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

	@Autowired
	private MerchantMapper merchantMapper;

	@Autowired
	private ProductMapper proMapper;
	
	public int saveMerchant(MerchantDTO dto) {
		return merchantMapper.saveMerchant(dto);
	}

	public List<MerchantDTO> queryMerchantList(Map map) {
		return merchantMapper.queryMerchantList(map);
	}

	public int updateMerchantState(MerchantDTO dto) {
		return merchantMapper.updateMerchantState(dto);
	}
	public int updateMerchant(MerchantDTO dto){
		
		return  merchantMapper.updateMerchant(dto);
	}

	public List<Product> queryProductsByMerchantIds (long id){
		
		return proMapper.queryProductsByMerchantId(id);
	}
}
