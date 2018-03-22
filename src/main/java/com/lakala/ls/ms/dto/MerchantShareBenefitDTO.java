package com.lakala.ls.ms.dto;

import java.math.BigDecimal;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class MerchantShareBenefitDTO {

    private String merchantId;

    private BigDecimal rate;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
