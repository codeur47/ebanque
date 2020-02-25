package io.yoropapers.ebanque.service.serviceImpl;

import java.math.BigDecimal;
import java.security.Principal;

import javax.transaction.Transactional;

import io.yoropapers.ebanque.utility.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.yoropapers.ebanque.dao.PrimaryAccountDao;
import io.yoropapers.ebanque.dao.SavingsAccountDao;
import io.yoropapers.ebanque.model.PrimaryAccount;
import io.yoropapers.ebanque.model.SavingsAccount;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.service.AccountService;
import io.yoropapers.ebanque.service.GenerateAccountNumber;
import io.yoropapers.ebanque.service.UserService;

/**
 * AccountServiceImpl
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private PrimaryAccountDao primaryAccountDao;
    private SavingsAccountDao savingsAccountDao;
    private GenerateAccountNumber generateAccountNumber;

    @Autowired
    public AccountServiceImpl(PrimaryAccountDao primaryAccountDao, SavingsAccountDao savingsAccountDao,
    GenerateAccountNumber generateAccountNumber) {
        this.primaryAccountDao = primaryAccountDao;
        this.savingsAccountDao = savingsAccountDao;
        this.generateAccountNumber = generateAccountNumber;
    }


    @Override
    public PrimaryAccount createPrimaryAccount(User user) {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal("0.0"));
        String initialUserPrimary = concatUserInitial(user.getLastName().toUpperCase().charAt(0),
                user.getUsername().toUpperCase().charAt(0));
        String accountNumber = generateAccountNumber.generateAccountNumber(new String("P"), initialUserPrimary);
        primaryAccount.setAccountNumber(accountNumber);
        primaryAccountDao.save(primaryAccount);
        return primaryAccountDao.findPrimaryAccountByAccountNumber(primaryAccount.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount(User user) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal("0.0"));
        String initialUserSavings = concatUserInitial(user.getLastName().toUpperCase().charAt(0),
                user.getUsername().toUpperCase().charAt(0));
        String accountNumber = generateAccountNumber.generateAccountNumber(new String("S"), initialUserSavings);
        savingsAccount.setAccountNumber(accountNumber);
        savingsAccountDao.save(savingsAccount);
        return savingsAccountDao.findSavingsAccountByAccountNumber(savingsAccount.getAccountNumber());
    }

    private String concatUserInitial(char lastNameInitial, char userNameInitial) {
        return lastNameInitial + "" + userNameInitial;
    }
}