package io.yoropapers.ebanque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.yoropapers.ebanque.utility.Transaction;

/**
 * SavingsTransaction
 */
@Entity
public class SavingsTransaction extends Transaction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savingsAccount;

    public SavingsTransaction() {
    }

    public SavingsTransaction(Long id, SavingsAccount savingsAccount) {
        this.id = id;
        this.savingsAccount = savingsAccount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SavingsAccount getSavingsAccount() {
        return this.savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public SavingsTransaction id(Long id) {
        this.id = id;
        return this;
    }

    public SavingsTransaction savingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", savingsAccount='" + getSavingsAccount() + "'" +
            "}";
    }

}
