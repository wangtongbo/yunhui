package com.lakala.ls.ms.dto;

import java.math.BigDecimal;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class CheckLoanApplyDTO {

    private String loanNo;
    private Long merchantId;
    private Long productId;
    private Long channelId;
    private String userId;
    private String merchantUserId;
    private BigDecimal loanAmount;
    private Integer phasedRepayPeriod;
    private String phasedRepayUnit;
    private BigDecimal repayInterest;
    private String merchantUserRegisterTime;
    private String applyTime;
    private String approveTime;
    private String loanTime;
    private String createTime;
    private BigDecimal merchantShareBenefitRate;
    private BigDecimal merchantShareBenefitAmount;
    private Long shareBenefitChannelId;
    private String shareBenefitChannelName;
    private BigDecimal channelShareBenefitRate;
    private BigDecimal channelShareBenefitAmount;
    private String clearDate;
    private String phaseState;
    private String phase;
    private String firstSuccessLoanFlag;

    private String productName;
    private String channelName;
    private String merchantName;
    private String identifier;
    private String mobile;

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getPhasedRepayPeriod() {
        return phasedRepayPeriod;
    }

    public void setPhasedRepayPeriod(Integer phasedRepayPeriod) {
        this.phasedRepayPeriod = phasedRepayPeriod;
    }

    public String getPhasedRepayUnit() {
        return phasedRepayUnit;
    }

    public void setPhasedRepayUnit(String phasedRepayUnit) {
        this.phasedRepayUnit = phasedRepayUnit;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public String getMerchantUserRegisterTime() {
        return merchantUserRegisterTime;
    }

    public void setMerchantUserRegisterTime(String merchantUserRegisterTime) {
        this.merchantUserRegisterTime = merchantUserRegisterTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMerchantShareBenefitRate() {
        return merchantShareBenefitRate;
    }

    public void setMerchantShareBenefitRate(BigDecimal merchantShareBenefitRate) {
        this.merchantShareBenefitRate = merchantShareBenefitRate;
    }

    public BigDecimal getMerchantShareBenefitAmount() {
        return merchantShareBenefitAmount;
    }

    public void setMerchantShareBenefitAmount(BigDecimal merchantShareBenefitAmount) {
        this.merchantShareBenefitAmount = merchantShareBenefitAmount;
    }

    public Long getShareBenefitChannelId() {
        return shareBenefitChannelId;
    }

    public void setShareBenefitChannelId(Long shareBenefitChannelId) {
        this.shareBenefitChannelId = shareBenefitChannelId;
    }

    public BigDecimal getChannelShareBenefitRate() {
        return channelShareBenefitRate;
    }

    public void setChannelShareBenefitRate(BigDecimal channelShareBenefitRate) {
        this.channelShareBenefitRate = channelShareBenefitRate;
    }

    public BigDecimal getChannelShareBenefitAmount() {
        return channelShareBenefitAmount;
    }

    public void setChannelShareBenefitAmount(BigDecimal channelShareBenefitAmount) {
        this.channelShareBenefitAmount = channelShareBenefitAmount;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getPhaseState() {
        return phaseState;
    }

    public void setPhaseState(String phaseState) {
        this.phaseState = phaseState;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstSuccessLoanFlag() {
        return firstSuccessLoanFlag;
    }

    public void setFirstSuccessLoanFlag(String firstSuccessLoanFlag) {
        this.firstSuccessLoanFlag = firstSuccessLoanFlag;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShareBenefitChannelName() {
        return shareBenefitChannelName;
    }

    public void setShareBenefitChannelName(String shareBenefitChannelName) {
        this.shareBenefitChannelName = shareBenefitChannelName;
    }
}
