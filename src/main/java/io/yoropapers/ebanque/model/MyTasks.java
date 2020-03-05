package io.yoropapers.ebanque.model;

import io.yoropapers.ebanque.utility.MyNotification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MyTasks extends MyNotification {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public MyTasks() {
    }

    public MyTasks(Date date, String title, String description, User user) {
        super(date, title, description, user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
