package io.yoropapers.ebanque.utility;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;


/**
 * Account
 */
@MappedSuperclass
public abstract class Account implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;    
    private String accountNumber;
    private BigDecimal accountBalance;  

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }


    public Account accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Account accountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
    
}