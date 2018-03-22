package com.lakala.ls.ms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Channel;
import com.lakala.ls.ms.domain.ChannelBenefit;
import com.lakala.ls.ms.domain.ChannelProduct;
import com.lakala.ls.ms.domain.ChannelProductInfo;
import com.lakala.ls.ms.dto.ChannelDTO;
import com.lakala.ls.ms.service.ChannelService;

@RestController
@RequestMapping(value = "/check")
public class ChannelController {
	
	
	@Autowired
	private ChannelService channelService;
	
//	@Autowired
//	private ChannelId chid;
//	
    @RequestMapping(value = "/addchannel", method = RequestMethod.POST)
    @ResponseBody
    public void uploadLoanAppayRecord(@RequestBody Map map) throws LsException {
    	String channlid ="1000001";// (String)chid.create();
    	long channelId = Long.parseLong(channlid);
		String channelName = (String)map.get("channelName");
		String channelLinkName = (String)map.get("channelLinkName");
		String channelLinkMobile = (String)map.get("channelLinkMobile");
		List products = (List) map.get("prods");
		
		Channel channel = new Channel();
		channel.setId(channelId);
		channel.setCreditState("0");
		channel.setState("0");
		channel.setLinkMobile(channelLinkMobile);
		channel.setLinkName(channelLinkName);
		channel.setName(channelName);
		
		ChannelDTO dto= new ChannelDTO();
		dto.setChannel(channel);
		List<ChannelBenefit> cbfList = new ArrayList<>();
		
		List<ChannelProduct> cbprList = new ArrayList<>();
		if(products!=null){
			for(int i=0;i<products.size();i++){
				Map inmap =(Map) products.get(i);
				
				String rate =(String) inmap.get("rate");
				String prod = (String)inmap.get("product");
				String prodid = prod.split("&")[0];
				String merchanid = prod.split("&")[1];
				
				ChannelBenefit cbf = new ChannelBenefit();
				cbf.setProductId(Long.parseLong(prodid));
				cbf.setChannelId(channelId);
				cbf.setRate(new BigDecimal(rate));
				ChannelProduct cp = new ChannelProduct();
				cp.setChannelId(channelId);
				cp.setProductId(Long.parseLong(prodid));
				cp.setState("1");
				cbfList.add(cbf);
				cbprList.add(cp);
				
			}
		}
		
		dto.setChannelBenefits(cbfList);
		dto.setChannelProducts(cbprList);
		channelService.addChannel(dto);
		
	 }

    
    @RequestMapping(value = "/querychannel", method = RequestMethod.GET)
    @ResponseBody
    public List<ChannelDTO> querychannel() throws LsException {
    	
    	
    	
    	return channelService.queryChannel();
    }
    
    @RequestMapping(value = "/updateChannelState", method = RequestMethod.POST)
    @ResponseBody
    public void updateChannelState(@RequestBody Map map) throws LsException {
    	String channelid = (String)map.get("channelid");
    	Integer state = (Integer)map.get("state");
    	channelService.updateChannelState(channelid, String.valueOf(state));
    	
    }
    @RequestMapping(value = "/delchannel", method = RequestMethod.POST)
    @ResponseBody
    public void delchannel(@RequestBody Map map) throws LsException {
    	String channelid = (String)map.get("channelid");
    	channelService.deleteChannle(channelid);
    }
    
    
    @RequestMapping(value = "/queryChannelProduct", method = RequestMethod.POST)
    @ResponseBody
    public   List<ChannelProductInfo> queryChannelProduct(@RequestBody Map map) throws LsException {
    	String channelid = (String)map.get("channelid");
    	
    	return channelService.queryChannelProduct(channelid);
    }
    
    
    @RequestMapping(value = "/updatechannel", method = RequestMethod.POST)
    @ResponseBody
    public void updatechannel(@RequestBody Map map) throws LsException {
    	
    	String channelid = (String)map.get("channelid");
		String channelName = (String)map.get("channelName");
		String channelLinkName = (String)map.get("channelLinkName");
		String channelLinkMobile = (String)map.get("channelLinkMobile");
		List products = (List) map.get("prods");
		
				  
		long channelId = Long.parseLong(channelid);
		Channel channel = new Channel();
		channel.setId(channelId);
		channel.setCreditState("0");
		channel.setState("0");
		channel.setLinkMobile(channelLinkMobile);
		channel.setLinkName(channelLinkName);
		channel.setName(channelName);
		
		ChannelDTO dto= new ChannelDTO();
		dto.setChannel(channel);
		List<ChannelBenefit> cbfList = new ArrayList<>();
		
		List<ChannelProduct> cbprList = new ArrayList<>();
		if(products!=null){
			for(int i=0;i<products.size();i++){
				Map inmap =(Map) products.get(i);
				
				String rate =(String) inmap.get("rate");
				String prod = (String)inmap.get("product");
				String prodid = prod.split("&")[0];
				String merchanid = prod.split("&")[1];
				
				ChannelBenefit cbf = new ChannelBenefit();
				cbf.setProductId(Long.parseLong(prodid));
				cbf.setChannelId(channelId);
				cbf.setRate(new BigDecimal(rate));
				ChannelProduct cp = new ChannelProduct();
				cp.setChannelId(channelId);
				cp.setProductId(Long.parseLong(prodid));
				cp.setState("1");
				cbfList.add(cbf);
				cbprList.add(cp);
				
			}
		}
		
		dto.setChannelBenefits(cbfList);
		dto.setChannelProducts(cbprList);
    	
    
		channelService.updateChannels(dto);
    }
    

}
