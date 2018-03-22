package com.lakala.ls.ms.dto;

import java.util.List;

import com.lakala.ls.ms.domain.Channel;
import com.lakala.ls.ms.domain.ChannelBenefit;
import com.lakala.ls.ms.domain.ChannelProduct;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class ChannelDTO {

    
    
    private Channel channel;
	
	List<ChannelBenefit> channelBenefits;
	
	List<ChannelProduct> channelProducts;

	private Integer prodCount;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List<ChannelBenefit> getChannelBenefits() {
		return channelBenefits;
	}

	public void setChannelBenefits(List<ChannelBenefit> channelBenefits) {
		this.channelBenefits = channelBenefits;
	}

	public List<ChannelProduct> getChannelProducts() {
		return channelProducts;
	}

	public void setChannelProducts(List<ChannelProduct> channelProducts) {
		this.channelProducts = channelProducts;
	}

	public Integer getProdCount() {
		return prodCount;
	}

	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}
	
	
	
}
