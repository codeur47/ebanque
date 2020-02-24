package io.yoropapers.ebanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.yoropapers.ebanque.model.SavingsAccount;

/**
 * SavingsAccountDao
 */
@Repository
public interface SavingsAccountDao extends JpaRepository<SavingsAccount, Long> {
    SavingsAccount findSavingsAccountByAccountNumber(String accountNumber);    
}