package com.accenture.bankledger.repository;

import com.accenture.bankledger.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLedgerRepository extends JpaRepository<Account, String> {
}
