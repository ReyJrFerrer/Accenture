package com.accenture.bankledger.service.impl;


import com.accenture.bankledger.dto.LedgerOpenAccountRequest;
import com.accenture.bankledger.dto.TermDepositDetails;
import com.accenture.bankledger.dto.TermDepositMaturityDetails;
import com.accenture.bankledger.exception.BadRequestException;
import com.accenture.bankledger.model.entity.Account;
import com.accenture.bankledger.repository.BankLedgerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BankLedgerServiceImplTest {
    @Mock
    private BankLedgerRepository bankLedgerRepository;
    @InjectMocks
    private BankLedgerServiceImpl bankLedgerService;

    @Test
    void testCreateAccount(){
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );

        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );

        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        Account savedAccount = new Account(
                "123456789", "John Sina", "123456",
                new BigDecimal("0.2"), new BigDecimal("100000.00"), 12,
                LocalDate.of(2023, 2, 21),
                LocalDate.of(2024, 2, 21)
        );

        when(bankLedgerRepository.save(any(Account.class))).thenReturn(savedAccount);

        Account result = bankLedgerService.createAccount(request);

        assertNotNull(result);
        assertEquals("123456789", result.getAccountNumber());
        assertEquals(LocalDate.of(2023,2,21), result.getEffectiveDate());
        assertEquals(LocalDate.of(2024,2,21), result.getExpiryDate());

    }

    @Test
    void testCreateAccount_WithDepositBelowMinimum_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("500.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithDepositAboveMaximum_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("200000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithMissingProductId_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest(null, termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithMissingRequiredDates_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                null,
                null
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithNullRequest_ThrowsException() {
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(null));

        assertEquals(400, exception.getErrorId());
        assertEquals("Request body is required.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithBlankProductId_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("   ", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithNullTermDepositDetails_ThrowsException() {
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", null, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithNullDepositAmount_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                null,
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithBlankEffectiveDate_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithBlankExpiryDate_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                ""
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithNullMaturityDetails_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, null);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithBlankAccountNumber_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "John Sina",
                "   "
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }

    @Test
    void testCreateAccount_WithBlankAccountName_ThrowsException() {
        TermDepositDetails termDepositDetails = new TermDepositDetails(
                new BigDecimal("0.2"),
                new BigDecimal("100000.00"),
                12,
                "21/02/2023",
                "21/02/2024"
        );
        TermDepositMaturityDetails maturityDetails = new TermDepositMaturityDetails(
                "",
                "123456789"
        );
        LedgerOpenAccountRequest request = new LedgerOpenAccountRequest("123456", termDepositDetails, maturityDetails);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bankLedgerService.createAccount(request));

        assertEquals(400, exception.getErrorId());
        assertEquals("Validation failed.", exception.getErrorMessage());
    }
}
