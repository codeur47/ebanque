package io.yoropapers.ebanque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.yoropapers.ebanque.utility.Transaction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PrimaryTransaction
 */
@Entity
public class PrimaryTransaction extends Transaction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    
    @ManyToOne
    @JoinColumn(name = "primary_account_id")
    private PrimaryAccount primaryAccount;

    public PrimaryTransaction() {
    }

    public PrimaryTransaction(Date date, String s, String typeTransaction, String status, BigDecimal amount, BigDecimal accountBalance, PrimaryAccount primaryAccount) {
        super(date,s,typeTransaction,status,amount,accountBalance);
        this.primaryAccount = primaryAccount;
    }
}