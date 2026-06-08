package com.accenture.bankledger.service;

import com.accenture.bankledger.dto.LedgerOpenAccountRequest;
import com.accenture.bankledger.model.entity.Account;

public interface BankLedgerService {
    Account createAccount(LedgerOpenAccountRequest request);

}
