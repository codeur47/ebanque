package io.yoropapers.ebanque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Recipient
 */
@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private User user;
    
    public Recipient() {
    }

    public Recipient(Long id, String firstName, String lastName, String email, String phone, String accountNumber, String description, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipient id(Long id) {
        this.id = id;
        return this;
    }

    public Recipient firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Recipient lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Recipient email(String email) {
        this.email = email;
        return this;
    }

    public Recipient phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Recipient accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Recipient description(String description) {
        this.description = description;
        return this;
    }

    public Recipient user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
    
}