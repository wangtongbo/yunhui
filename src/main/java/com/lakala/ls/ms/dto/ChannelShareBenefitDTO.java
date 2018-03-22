package com.lakala.ls.ms.dto;

import java.math.BigDecimal;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class ChannelShareBenefitDTO {

    private Long channelId;

    private Long productId;

    private BigDecimal rate;

    private Long childChannelId;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getChildChannelId() {
        return childChannelId;
    }

    public void setChildChannelId(Long childChannelId) {
        this.childChannelId = childChannelId;
    }
}
