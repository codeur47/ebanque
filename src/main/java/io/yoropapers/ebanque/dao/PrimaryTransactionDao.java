package io.yoropapers.ebanque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.yoropapers.ebanque.model.PrimaryTransaction;
import org.springframework.stereotype.Repository;

/**
 * PrimaryTransactionDao
 */
@Repository
public interface PrimaryTransactionDao extends JpaRepository<PrimaryTransaction, Long> {
    List<PrimaryTransaction> findAll();
}