package com.lakala.ls.ms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.service.MerchantService;

@RestController
@RequestMapping(value = "/merchant")
public class MerchantContoller {

	@Autowired
	private MerchantService merchantService;

	
//	@PreAuthorize("hasRole('ROLE_MERCHANT')")
	@RequestMapping(value = "/saveMerchant", method = RequestMethod.POST)
	@ResponseBody
	public int saveMerchant(@RequestBody MerchantDTO merchantDTO) throws LsException {
		Long merchant_Id = merchantDTO.getMerchantId();
		if(merchant_Id==null){
			merchantDTO.setState("2");
			return merchantService.saveMerchant(merchantDTO);
		}else{
			
			return merchantService.updateMerchant(merchantDTO);
		}
		
		
	}

//	@PreAuthorize("hasRole('ROLE_MERCHANT')")
	@RequestMapping(value = "/queryMerchantList", method = RequestMethod.POST)
	@ResponseBody
	public List<MerchantDTO> queryMerchantList(@RequestParam(name = "merchantName",required=false) String merchantName)
			throws LsException{
		Map map = new HashMap<>();
		map.put("merchantName", merchantName);
		return merchantService.queryMerchantList(map);
	}

//	@PreAuthorize("hasRole('ROLE_MERCHANT')")
	@RequestMapping(value = "/updateMerchantState", method = RequestMethod.POST)
    @ResponseBody
    public int updateMerchantState(@RequestBody MerchantDTO merchantDTO) throws LsException {
		return merchantService.updateMerchantState(merchantDTO);
    }
	
	
	
	@RequestMapping(value = "/queryProductByMerchant", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> queryProductByMerchant(@RequestBody Map map) throws LsException{
		String id = (String)map.get("id");
		
		return  merchantService.queryProductsByMerchantIds(Long.parseLong(id));
		
	}
	
	

}