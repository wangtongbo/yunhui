package com.lakala.ls.ms.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MPBenefitRule {
	
private long id;
	
	private long mix;
	
	private long max;
	
	private long merchantId;
	
	private long productId;
	
	private BigDecimal firstRate;
	
	private BigDecimal rate;
	
	private Date create_time;
	
	private Date update_time;
	
	
	private String productName;
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public MPBenefitRule(long mix,long max, long merchatId,long productId, BigDecimal firstRate, BigDecimal rate){
		this.mix=mix;
		this.max=max;
		this.merchantId=merchatId;
		this.productId=productId;
		this.rate=rate;
		this.firstRate=firstRate;
				
		
	}
	public MPBenefitRule(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMix() {
		return mix;
	}

	public void setMix(long mix) {
		this.mix = mix;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getMerchatId() {
		return merchantId;
	}

	public void setMerchatId(long merchatId) {
		this.merchantId = merchatId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public BigDecimal getFirstRate() {
		return firstRate;
	}

	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
	

}
