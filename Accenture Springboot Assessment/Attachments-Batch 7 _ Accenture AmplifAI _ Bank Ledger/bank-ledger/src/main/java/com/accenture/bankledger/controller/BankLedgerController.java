package com.accenture.bankledger.controller;

import com.accenture.bankledger.dto.LedgerOpenAccountRequest;
import com.accenture.bankledger.dto.LedgerOpenAccountResponse;
import com.accenture.bankledger.service.BankLedgerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class BankLedgerController {
    private Logger log = Logger.getLogger(getClass().getName());
    private BankLedgerService bankLedgerService;

    BankLedgerController(BankLedgerService bankLedgerService){
        this.bankLedgerService = bankLedgerService;
    }

    @PostMapping("/createAccount")
    public LedgerOpenAccountResponse createAccount(@RequestBody LedgerOpenAccountRequest request){
        log.info("Creating account for productId: "+request.getProductId());

        bankLedgerService.createAccount(request);
        return new LedgerOpenAccountResponse(request.getTermDepositMaturityDetails().getAccountNumber());

    }
}
