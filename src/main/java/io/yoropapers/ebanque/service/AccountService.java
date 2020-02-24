package io.yoropapers.ebanque.service;

import java.security.Principal;

import io.yoropapers.ebanque.model.PrimaryAccount;
import io.yoropapers.ebanque.model.SavingsAccount;
import io.yoropapers.ebanque.model.User;

/**
 * AccountService
 */
public interface AccountService {
    PrimaryAccount createPrimaryAccount(User user);
    SavingsAccount createSavingsAccount(User user);
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}