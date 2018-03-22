package com.lakala.ls.ms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Product;
import com.lakala.ls.ms.domain.RankManager;
import com.lakala.ls.ms.dto.MerchantDTO;
import com.lakala.ls.ms.service.RankManagerService;

@RestController
@RequestMapping(value = "/rank")
public class RankManagerController {
	
	@Autowired
	private RankManagerService rankService;
	
	@RequestMapping(value = "/saveRank", method = RequestMethod.POST)
	@ResponseBody
	public void saveRank(@RequestParam("type")String type,
			@RequestParam("rank")String rank,
			@RequestParam("productid")String productid,
			@RequestParam(value="rankid",required=false)String rankid) throws LsException {
		if(rankid==null||rankid=="" ||rankid=="null" ||rankid.length()==0){
			RankManager rankM = new RankManager();
			rankM.setType(type);
			rankM.setRank(Long.parseLong(rank));
			rankM.setProductid(Long.parseLong(productid));
			rankService.addRank(rankM);
		}else{
			RankManager rankM = new RankManager();
			rankM.setId(Long.parseLong(rankid));
			rankM.setType(type);
			rankM.setRank(Long.parseLong(rank));
			rankM.setProductid(Long.parseLong(productid));
			rankService.updateRank(rankM);
		}
	}

//	@PreAuthorize("hasRole('ROLE_MERCHANT')")
	@RequestMapping(value = "/queryRanks", method = RequestMethod.POST)
	@ResponseBody
	public List<RankManager> queryRanks()
			throws LsException{
		return rankService.queryRanks();
	}

	
	
	
	@RequestMapping(value = "/deleteRank", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRank(@RequestBody Map map) throws LsException{
		String id = (String)map.get("id");
		
		rankService.deleteRank(Long.parseLong(id));
		
	}
	

}
