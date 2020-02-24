package io.yoropapers.ebanque.utility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.MappedSuperclass;

/**
 * Transaction
 */
@MappedSuperclass
public class Transaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date date;
    
    private String description;
    
    private String type;
    
    private String status;
    
    private double amount;
    
    private BigDecimal availableBalance;

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

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return this.availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
    
}