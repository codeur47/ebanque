package io.yoropapers.ebanque.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Appointment
 */
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Date date;
    private String location;
    private String description;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    public Appointment() {
    }

    public Appointment(Long id, Date date, String location, String description, boolean confirmed, User user) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.description = description;
        this.confirmed = confirmed;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public boolean getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appointment id(Long id) {
        this.id = id;
        return this;
    }

    public Appointment date(Date date) {
        this.date = date;
        return this;
    }

    public Appointment location(String location) {
        this.location = location;
        return this;
    }

    public Appointment description(String description) {
        this.description = description;
        return this;
    }

    public Appointment confirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Appointment user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", location='" + getLocation() + "'" +
            ", description='" + getDescription() + "'" +
            ", confirmed='" + isConfirmed() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}