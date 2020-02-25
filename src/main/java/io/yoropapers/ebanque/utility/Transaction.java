package io.yoropapers.ebanque.utility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transaction
 */
@MappedSuperclass
public class Transaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private String description;
    
    private String type;
    
    private String status;
    
    private BigDecimal amount;
    
    private BigDecimal availableBalance;

    public Transaction() {
    }

    public Transaction(Date date, String description, String type, String status, BigDecimal amount, BigDecimal availableBalance) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
    }



    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return this.availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
    
}