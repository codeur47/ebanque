package io.yoropapers.ebanque.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.yoropapers.ebanque.utility.Account;
import java.util.List;

/**
 * PrimaryAccount
 */
@Entity
public class PrimaryAccount extends Account {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrimaryTransaction> primaryTransactionList;
    

    public PrimaryAccount() {
    }

    public PrimaryAccount(Long id, List<PrimaryTransaction> primaryTransactionList) {
        this.id = id;
        this.primaryTransactionList = primaryTransactionList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PrimaryTransaction> getPrimaryTransactionList() {
        return this.primaryTransactionList;
    }

    public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
        this.primaryTransactionList = primaryTransactionList;
    }

    public PrimaryAccount id(Long id) {
        this.id = id;
        return this;
    }

    public PrimaryAccount primaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
        this.primaryTransactionList = primaryTransactionList;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", primaryTransactionList='" + getPrimaryTransactionList() + "'" +
            "}";
    }


}