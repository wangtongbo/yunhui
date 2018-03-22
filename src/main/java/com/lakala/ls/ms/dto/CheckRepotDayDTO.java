package com.lakala.ls.ms.dto;

import java.math.BigDecimal;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class CheckRepotDayDTO {

    private String reportDate;

    private Integer applyClickUserNum;

    private Integer applyClickNum;

    private Integer dayLoanBillNum;

    private Integer dayLoanUserNum;

    private Integer newLoanSuccessBillNum;

    private Integer againLoanSuccessBillNum;

    private Integer newLoanSuccessUserNum;

    private Integer againLoanSuccessUserNum;

    private BigDecimal newLoanSuccessAmountNum;

    private BigDecimal againLoanSuccessAmountNum;

    private BigDecimal newShareBenefitAmountNum;

    private BigDecimal againShareBenefitAmountNum;

    private Long productId;

    private String productName;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getApplyClickUserNum() {
        return applyClickUserNum;
    }

    public void setApplyClickUserNum(Integer applyClickUserNum) {
        this.applyClickUserNum = applyClickUserNum;
    }

    public Integer getApplyClickNum() {
        return applyClickNum;
    }

    public void setApplyClickNum(Integer applyClickNum) {
        this.applyClickNum = applyClickNum;
    }

    public Integer getDayLoanBillNum() {
        return dayLoanBillNum;
    }

    public void setDayLoanBillNum(Integer dayLoanBillNum) {
        this.dayLoanBillNum = dayLoanBillNum;
    }

    public Integer getDayLoanUserNum() {
        return dayLoanUserNum;
    }

    public void setDayLoanUserNum(Integer dayLoanUserNum) {
        this.dayLoanUserNum = dayLoanUserNum;
    }

    public Integer getNewLoanSuccessBillNum() {
        return newLoanSuccessBillNum;
    }

    public void setNewLoanSuccessBillNum(Integer newLoanSuccessBillNum) {
        this.newLoanSuccessBillNum = newLoanSuccessBillNum;
    }

    public Integer getAgainLoanSuccessBillNum() {
        return againLoanSuccessBillNum;
    }

    public void setAgainLoanSuccessBillNum(Integer againLoanSuccessBillNum) {
        this.againLoanSuccessBillNum = againLoanSuccessBillNum;
    }

    public Integer getNewLoanSuccessUserNum() {
        return newLoanSuccessUserNum;
    }

    public void setNewLoanSuccessUserNum(Integer newLoanSuccessUserNum) {
        this.newLoanSuccessUserNum = newLoanSuccessUserNum;
    }

    public Integer getAgainLoanSuccessUserNum() {
        return againLoanSuccessUserNum;
    }

    public void setAgainLoanSuccessUserNum(Integer againLoanSuccessUserNum) {
        this.againLoanSuccessUserNum = againLoanSuccessUserNum;
    }

    public BigDecimal getNewLoanSuccessAmountNum() {
        return newLoanSuccessAmountNum;
    }

    public void setNewLoanSuccessAmountNum(BigDecimal newLoanSuccessAmountNum) {
        this.newLoanSuccessAmountNum = newLoanSuccessAmountNum;
    }

    public BigDecimal getAgainLoanSuccessAmountNum() {
        return againLoanSuccessAmountNum;
    }

    public void setAgainLoanSuccessAmountNum(BigDecimal againLoanSuccessAmountNum) {
        this.againLoanSuccessAmountNum = againLoanSuccessAmountNum;
    }

    public BigDecimal getNewShareBenefitAmountNum() {
        return newShareBenefitAmountNum;
    }

    public void setNewShareBenefitAmountNum(BigDecimal newShareBenefitAmountNum) {
        this.newShareBenefitAmountNum = newShareBenefitAmountNum;
    }

    public BigDecimal getAgainShareBenefitAmountNum() {
        return againShareBenefitAmountNum;
    }

    public void setAgainShareBenefitAmountNum(BigDecimal againShareBenefitAmountNum) {
        this.againShareBenefitAmountNum = againShareBenefitAmountNum;
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
}
