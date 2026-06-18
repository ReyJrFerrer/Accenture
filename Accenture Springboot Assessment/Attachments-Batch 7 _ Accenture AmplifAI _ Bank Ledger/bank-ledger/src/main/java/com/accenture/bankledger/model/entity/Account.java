package com.accenture.bankledger.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number", length = 20)
    private String accountNumber;

    @Column(name = "account_name", length = 100)
    private String accountName;

    @Column(columnDefinition = "VARCHAR(10)")
    private String productId;

    @Column(nullable = false)
    private BigDecimal interestRate;

    @Column(name = "deposit_amount", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal depositAmount;

    @Column(name = "term_months")
    private int termMonths;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    public Account() {
    }

    public Account(String accountNumber, String accountName, String productId,
                   BigDecimal interestRate, BigDecimal depositAmount,
                   int termMonths,
                   LocalDate effectiveDate, LocalDate expiryDate) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.productId = productId;
        this.interestRate = interestRate;
        this.depositAmount = depositAmount;
        this.termMonths = termMonths;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
