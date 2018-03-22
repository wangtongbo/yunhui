package com.lakala.ls.ms.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户贷款申请记录实体类
 * @author wenhemin
 */
public class UserLoanApplyDTO {

    private String userId;
    private Long productId;
    private String productName;
    private String channelName;
    private String iconUrl;
    private Long channelId;
    private Long merchantId;
    private String merchantName;
    private String loanNo;
    private BigDecimal applyAmount;
    private String applyTime;
    private String loanUsage;
    private String loanRate;
    private BigDecimal loanAmount;
    private String loanTime;
    private String repayMode;
    private Integer phasedRepayPeriod;
    private String phasedRepayUnit;
    private String phasedRepayAmount;
    private BigDecimal repayAmount;
    private String state;
    private String refuseReason;
    private String createTime;
    private String updateTime;
    private String mobile;
    private String merchantUserId;
    private String merchantUserRegisterTime;
    private String approveTime;
    private BigDecimal repayInterest;
    private String phasedRepayPeriodText;
    private String identifier;
    private BigDecimal shareBenefitRate;
    private BigDecimal shareBenefitAmount;
    private String firstSuccessLoanFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getLoanUsage() {
        return loanUsage;
    }

    public void setLoanUsage(String loanUsage) {
        this.loanUsage = loanUsage;
    }

    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

    public String getRepayMode() {
        return repayMode;
    }

    public void setRepayMode(String repayMode) {
        this.repayMode = repayMode;
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

    public String getPhasedRepayAmount() {
        return phasedRepayAmount;
    }

    public void setPhasedRepayAmount(String phasedRepayAmount) {
        this.phasedRepayAmount = phasedRepayAmount;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getPhasedRepayPeriodText() {
        return phasedRepayPeriodText;
    }

    public void setPhasedRepayPeriodText(String phasedRepayPeriodText) {
        this.phasedRepayPeriodText = phasedRepayPeriodText;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public String getMerchantUserRegisterTime() {
        return merchantUserRegisterTime;
    }

    public void setMerchantUserRegisterTime(String merchantUserRegisterTime) {
        this.merchantUserRegisterTime = merchantUserRegisterTime;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getShareBenefitRate() {
        return shareBenefitRate;
    }

    public void setShareBenefitRate(BigDecimal shareBenefitRate) {
        this.shareBenefitRate = shareBenefitRate;
    }

    public BigDecimal getShareBenefitAmount() {
        return shareBenefitAmount;
    }

    public void setShareBenefitAmount(BigDecimal shareBenefitAmount) {
        this.shareBenefitAmount = shareBenefitAmount;
    }

    public String getFirstSuccessLoanFlag() {
        return firstSuccessLoanFlag;
    }

    public void setFirstSuccessLoanFlag(String firstSuccessLoanFlag) {
        this.firstSuccessLoanFlag = firstSuccessLoanFlag;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
