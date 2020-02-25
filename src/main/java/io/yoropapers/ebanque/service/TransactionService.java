package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface TransactionService {
    List<PrimaryTransaction> findPrimaryTransactionListByUsername(String username);
    List<SavingsTransaction> findSavingsTransactionListByUsername(String username);
    void betweenAccountsTransfer(String transferFrom, String transferTo, BigDecimal amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;
    void toSomeoneElseTransfer(Recipient recipient, String accountType, BigDecimal amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);
    void deposit(String accountType, BigDecimal amount, Principal principal);
    void withdraw(String accountType, BigDecimal amount, Principal principal);
}
