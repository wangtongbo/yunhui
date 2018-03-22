package com.lakala.ls.ms.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	
	
	private long merchantId;
	
	private String prodName;
	
    private MultipartFile imgfile;
    
    private String prodType;
    private String amountInfo;
    private String feature;
    private String provider;
    private String rate;
    private String life;
    private String prodDec;
    private String applyCondition;
    private String requiredDoc;
    private String authDesc;
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public MultipartFile getImgfile() {
		return imgfile;
	}
	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getAmountInfo() {
		return amountInfo;
	}
	public void setAmountInfo(String amountInfo) {
		this.amountInfo = amountInfo;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getLife() {
		return life;
	}
	public void setLife(String life) {
		this.life = life;
	}
	public String getProdDec() {
		return prodDec;
	}
	public void setProdDec(String prodDec) {
		this.prodDec = prodDec;
	}
	public String getApplyCondition() {
		return applyCondition;
	}
	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}
	public String getRequiredDoc() {
		return requiredDoc;
	}
	public void setRequiredDoc(String requiredDoc) {
		this.requiredDoc = requiredDoc;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
    
    
    

}
