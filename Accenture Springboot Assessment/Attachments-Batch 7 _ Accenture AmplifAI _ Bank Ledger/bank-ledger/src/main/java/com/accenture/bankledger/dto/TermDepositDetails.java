package com.accenture.bankledger.dto;

import java.math.BigDecimal;

public class TermDepositDetails {
    private BigDecimal interestRate;
    private BigDecimal depositAmount;
    private int termMonths;
    private String effectiveDate;
    private String expiryDate;

    public TermDepositDetails(BigDecimal interestRate, BigDecimal depositAmount, int termMonths, String effectiveDate, String expiryDate){
        this.interestRate = interestRate;
        this.depositAmount = depositAmount;
        this.termMonths = termMonths;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


}
