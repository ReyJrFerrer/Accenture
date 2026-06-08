package com.accenture.bankledger.service.impl;

import com.accenture.bankledger.dto.LedgerOpenAccountRequest;
import com.accenture.bankledger.exception.BadRequestException;
import com.accenture.bankledger.model.entity.Account;
import com.accenture.bankledger.repository.BankLedgerRepository;
import com.accenture.bankledger.service.BankLedgerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankLedgerServiceImpl implements BankLedgerService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final BigDecimal MIN_DEPOSIT_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_DEPOSIT_AMOUNT = new BigDecimal("100000.00");
    private final BankLedgerRepository bankLedgerRepository;

    public BankLedgerServiceImpl(BankLedgerRepository bankLedgerRepository) {
        this.bankLedgerRepository = bankLedgerRepository;
    }

    @Override
    public Account createAccount(LedgerOpenAccountRequest request) {
        validateRequest(request);
        Account account = generateAccount(request);
        return bankLedgerRepository.save(account);
    }

    private void validateRequest(LedgerOpenAccountRequest request) {
        Map<String, Object> errors = new HashMap<>();

        if (request == null) {
            throw new BadRequestException(400, "Request body is required.", null);
        }

        if (request.getProductId() == null || request.getProductId().isBlank()) {
            errors.put("productId", "Product ID is required.");
        }

        if (request.getTermDepositDetails() == null) {
            errors.put("termDepositDetails", "Term deposit details are required.");
        } else {
            BigDecimal depositAmount = request.getTermDepositDetails().getDepositAmount();
            if (depositAmount == null) {
                errors.put("depositAmount", "Deposit amount is required.");
            } else if (depositAmount.compareTo(MIN_DEPOSIT_AMOUNT) < 0) {
                errors.put("depositAmount", "Deposit amount must be at least " + MIN_DEPOSIT_AMOUNT + ".");
            } else if (depositAmount.compareTo(MAX_DEPOSIT_AMOUNT) > 0) {
                errors.put("depositAmount", "Deposit amount must not exceed " + MAX_DEPOSIT_AMOUNT + ".");
            }

            if (request.getTermDepositDetails().getEffectiveDate() == null ||
                    request.getTermDepositDetails().getEffectiveDate().isBlank()) {
                errors.put("effectiveDate", "Effective date is required.");
            }
            if (request.getTermDepositDetails().getExpiryDate() == null ||
                    request.getTermDepositDetails().getExpiryDate().isBlank()) {
                errors.put("expiryDate", "Expiry date is required.");
            }
        }

        if (request.getTermDepositMaturityDetails() == null) {
            errors.put("termDepositMaturityDetails", "Maturity details are required.");
        } else {
            if (request.getTermDepositMaturityDetails().getAccountNumber() == null ||
                    request.getTermDepositMaturityDetails().getAccountNumber().isBlank()) {
                errors.put("accountNumber", "Account number is required.");
            }
            if (request.getTermDepositMaturityDetails().getAccountName() == null ||
                    request.getTermDepositMaturityDetails().getAccountName().isBlank()) {
                errors.put("accountName", "Account name is required.");
            }
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(400, "Validation failed.", errors);
        }
    }

    private Account generateAccount(LedgerOpenAccountRequest request) {
        return new Account(
                request.getTermDepositMaturityDetails().getAccountNumber(),
                request.getTermDepositMaturityDetails().getAccountName(),
                request.getProductId(),
                request.getTermDepositDetails().getInterestRate(),
                request.getTermDepositDetails().getDepositAmount(),
                request.getTermDepositDetails().getTermMonths(),
                convertToLocalDate(request.getTermDepositDetails().getEffectiveDate()),
                convertToLocalDate(request.getTermDepositDetails().getExpiryDate())
        );
    }

    private LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
