package io.yoropapers.ebanque.service;

import java.security.Principal;
import java.util.List;

import io.yoropapers.ebanque.model.PrimaryAccount;
import io.yoropapers.ebanque.model.SavingsAccount;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.utility.Account;

/**
 * AccountService
 */
public interface AccountService {
    PrimaryAccount createPrimaryAccount(User user);
    SavingsAccount createSavingsAccount(User user);
}