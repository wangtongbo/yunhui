package com.lakala.ls.ms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lakala.ls.ms.domain.Channel;
import com.lakala.ls.ms.domain.ChannelProduct;
import com.lakala.ls.ms.domain.ChannelProductInfo;
import com.lakala.ls.ms.dto.ChannelDTO;


@Mapper
public interface ChannelMapper {

    public List<Channel> queryChannels();
    
    
    public int saveChannel(Channel channle);
	
	public void addChannelProduct(ChannelProduct channelPro);
	
	public void addChannelProductS(List<ChannelProduct> prodlist);
	
	
	public List<Channel> queryChannel();
	
	public List<ChannelProduct> queryChannelProducts(long channelid);
	
	
	public int updateChannelState(long channelid,String state);
	
	
	public List<ChannelProductInfo> queryPrductInfo(String channelid);
	
	public int updateChannel(Channel channle);
	
	
	public int deleteChannel(String channelid);
	
	public int deleteChannelProduct(String channelid);
}