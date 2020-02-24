package io.yoropapers.ebanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.yoropapers.ebanque.model.PrimaryAccount;

/**
 * PrimaryAccountDao
 */
@Repository
public interface PrimaryAccountDao extends JpaRepository<PrimaryAccount, Long> {
    PrimaryAccount findPrimaryAccountByAccountNumber(String accountNumber);
}