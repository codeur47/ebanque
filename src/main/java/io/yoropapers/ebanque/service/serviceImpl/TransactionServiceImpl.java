package io.yoropapers.ebanque.service.serviceImpl;

import io.yoropapers.ebanque.dao.*;
import io.yoropapers.ebanque.model.*;
import io.yoropapers.ebanque.service.TransactionService;
import io.yoropapers.ebanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private UserService userService;
    private PrimaryTransactionDao primaryTransactionDao;
    private SavingsTransactionDao savingsTransactionDao;
    private PrimaryAccountDao primaryAccountDao;
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    public TransactionServiceImpl(@Lazy UserService userService, PrimaryTransactionDao primaryTransactionDao, SavingsTransactionDao savingsTransactionDao, PrimaryAccountDao primaryAccountDao, SavingsAccountDao savingsAccountDao) {
        this.userService = userService;
        this.primaryTransactionDao = primaryTransactionDao;
        this.savingsTransactionDao = savingsTransactionDao;
        this.primaryAccountDao = primaryAccountDao;
        this.savingsAccountDao = savingsAccountDao;
    }

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionListByUsername(String username) {
        return userService.findUserByUsername(username).getPrimaryAccount().getPrimaryTransactionList();
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionListByUsername(String username) {
        return userService.findUserByUsername(username).getSavingsAccount().getSavingsTransactionList();
    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, BigDecimal amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(amount));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(amount));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(),"Virement du compte " + transferFrom + " au compte " + transferTo, "Virement",
                    "Terminé",amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(amount));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(amount));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(),"Virement du compte " + transferFrom + " au compte " + transferTo, "Virement",
                    "Terminé", amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        } else throw new Exception("Virement invalide");
    }

    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, BigDecimal amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Courant")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(amount));
            primaryAccountDao.save(primaryAccount);
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(),
                    "Virement du compte " + accountType + " au compte de " + recipient.getAccountNumber(), "Virement", "Terminé",
                    amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if (accountType.equalsIgnoreCase("Epargne")) {
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(amount));
            savingsAccountDao.save(savingsAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(),
                    "Virement du compte " + accountType + " au compte de " + recipient.getAccountNumber(), "Virement", "Terminé",
                    amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }

    @Override
    public void deposit(String accountType, BigDecimal amount, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        if (accountType.equalsIgnoreCase("Courant")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(amount));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Virement sur le compte Courant", "Virement", "Terminé", amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if(accountType.equalsIgnoreCase("Epargne")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(amount));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Virement sur le compte Epargne", "Virement", "Terminé", amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }

    @Override
    public void withdraw(String accountType, BigDecimal amount, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        if (accountType.equalsIgnoreCase("Courant")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(amount));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Retrait du compte courant", "Retrait", "Terminé", amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if(accountType.equalsIgnoreCase("Epargne")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(amount));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Retrait du compte epargne", "Retrait", "Terminé", amount, savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }
}
