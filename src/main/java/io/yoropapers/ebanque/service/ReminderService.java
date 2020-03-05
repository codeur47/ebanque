package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.Reminder;

import java.util.List;

public interface ReminderService {

    void createReminder(Reminder reminder);
    void deleteReminder(Reminder reminder);
    List<Reminder> findAllByUserUsername(String username);
    List<Reminder> findAll();
    List<Reminder> findAllByUserUsernameOrderByDateAsc(String uername);
    List<Reminder> findAllByUserUsernameOrderByDateDesc(String username);

}
