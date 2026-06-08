package com.accenture.bankledger.dto;

public class TermDepositMaturityDetails {
    private String accountName;
    private String accountNumber;

    public TermDepositMaturityDetails(String accountName, String accountNumber){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }
    public String getAccountName() {
        return accountName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
