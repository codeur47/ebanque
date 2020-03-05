package io.yoropapers.ebanque.utility;

import io.yoropapers.ebanque.model.User;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class MyNotification {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public MyNotification() {
    }

    public MyNotification(Date date, String title, String description, User user){
        this.date = date;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
