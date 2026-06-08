package com.accenture.bankledger.repository;

import com.accenture.bankledger.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankLedgerRepository extends JpaRepository<Account, String> {
}
