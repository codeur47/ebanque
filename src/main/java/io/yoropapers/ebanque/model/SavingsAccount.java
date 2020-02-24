package io.yoropapers.ebanque.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.yoropapers.ebanque.utility.Account;

/**
 * SavingsAccount
 */
@Entity
public class SavingsAccount extends Account  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactionList;


    public SavingsAccount() {
    }

    public SavingsAccount(Long id, List<SavingsTransaction> savingsTransactionList) {
        this.id = id;
        this.savingsTransactionList = savingsTransactionList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SavingsTransaction> getSavingsTransactionList() {
        return this.savingsTransactionList;
    }

    public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
        this.savingsTransactionList = savingsTransactionList;
    }

    public SavingsAccount id(Long id) {
        this.id = id;
        return this;
    }

    public SavingsAccount savingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
        this.savingsTransactionList = savingsTransactionList;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", savingsTransactionList='" + getSavingsTransactionList() + "'" +
            "}";
    }


}