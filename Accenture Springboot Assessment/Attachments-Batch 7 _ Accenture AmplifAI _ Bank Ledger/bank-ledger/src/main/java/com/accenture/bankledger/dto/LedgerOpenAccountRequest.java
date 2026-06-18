package com.accenture.bankledger.dto;

public class LedgerOpenAccountRequest {
    private String productId;
    private TermDepositDetails termDepositDetails;
    private TermDepositMaturityDetails termDepositMaturityDetails;

    public LedgerOpenAccountRequest(
            String productId,
            TermDepositDetails termDepositDetails,
            TermDepositMaturityDetails termDepositMaturityDetails) {
        this.productId = productId;
        this.termDepositDetails = termDepositDetails;
        this.termDepositMaturityDetails = termDepositMaturityDetails;
    }

    public String getProductId() {
        return productId;
    }
    public TermDepositDetails getTermDepositDetails() {
        return termDepositDetails;
    }
    public TermDepositMaturityDetails getTermDepositMaturityDetails() {
        return termDepositMaturityDetails;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setTermDepositDetails(TermDepositDetails termDepositDetails) {
        this.termDepositDetails = termDepositDetails;
    }
    public void setTermDepositMaturityDetails(
            TermDepositMaturityDetails termDepositMaturityDetails) {
        this.termDepositMaturityDetails = termDepositMaturityDetails;
    }

}
