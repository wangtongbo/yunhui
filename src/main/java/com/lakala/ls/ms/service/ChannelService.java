package com.lakala.ls.ms.service;

import java.util.ArrayList;
import java.util.List;

import com.lakala.ls.ms.dto.ChannelDTO;
import com.lakala.ls.ms.mapper.ChannelBenefitMapper;
import com.lakala.ls.ms.mapper.ChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakala.ls.LsException;
import com.lakala.ls.ms.domain.Channel;
import com.lakala.ls.ms.domain.ChannelBenefit;
import com.lakala.ls.ms.domain.ChannelProduct;
import com.lakala.ls.ms.domain.ChannelProductInfo;


/**
 * Created by whmyy on 17/5/10.
 */
@Service(value = "channelService")
public class ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    public List<Channel> queryChannels() {
        return channelMapper.queryChannels();
    }
    
	
	@Autowired
	private ChannelBenefitMapper cbMapper;
	

	
	public void addChannel (ChannelDTO cldto) throws LsException{
		
		channelMapper.saveChannel(cldto.getChannel());
		
		List<ChannelBenefit > cbs = cldto.getChannelBenefits();
		for(int k =0;k<cbs.size();k++){
			cbMapper.addChannelBenefit(cbs.get(k));
		}
		
		if(cldto.getChannelProducts().size()>0){
			channelMapper.addChannelProductS(cldto.getChannelProducts());
		}
		
		
	}
	
	public List<ChannelDTO> queryChannel(){
		
		List<ChannelDTO> list = new ArrayList<>();
		List<Channel> chList =channelMapper.queryChannel();
		for(int i=0;i<chList.size();i++){
			ChannelDTO channledto= new ChannelDTO();
			Channel cl = chList.get(i);
			List<ChannelProduct> prods = channelMapper.queryChannelProducts(cl.getId());
			List<ChannelBenefit> benefits = cbMapper.queryChannelBenefitByChannelId(cl.getId());
			channledto.setChannel(cl);
			channledto.setChannelBenefits(benefits);
			channledto.setChannelProducts(prods);
			channledto.setProdCount(prods.size());
			list.add(channledto);
		}
		return list;
	}
	
	
	public int updateChannelState(String channelid,String state){
		
		int k = channelMapper.updateChannelState(Long.parseLong(channelid), state);
		return k;
	}
	
	
	
	public  List<ChannelProductInfo> queryChannelProduct(String channelid){
		
		return channelMapper.queryPrductInfo(channelid);
		
	}
	
	public void deleteChannle(String channelid){
		
		channelMapper.deleteChannel(channelid);
		channelMapper.deleteChannelProduct(channelid);
		cbMapper.deleteChannelBenefit(Long.parseLong(channelid));
	}
	
	public void updateChannels(ChannelDTO cldto){
		
		channelMapper.updateChannel(cldto.getChannel());
		
		channelMapper.deleteChannelProduct(String.valueOf(cldto.getChannel().getId()));
		cbMapper.deleteChannelBenefit(cldto.getChannel().getId());
		
		
		List<ChannelBenefit > cbs = cldto.getChannelBenefits();
		for(int k =0;k<cbs.size();k++){
			cbMapper.addChannelBenefit(cbs.get(k));
		}
		
		channelMapper.addChannelProductS(cldto.getChannelProducts());
	}
	
    

}
