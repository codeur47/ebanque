package io.yoropapers.ebanque.dao;

import io.yoropapers.ebanque.model.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsTransactionDao extends JpaRepository<SavingsTransaction, Long> {
    List<SavingsTransaction> findAll();
}
