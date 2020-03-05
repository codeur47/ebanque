package io.yoropapers.ebanque.dao;

import io.yoropapers.ebanque.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderDao extends JpaRepository<Reminder, Long> {

    List<Reminder> findAllByUserUsername(String username);
    List<Reminder> findAll();
    List<Reminder> findAllByUserUsernameOrderByDateAsc(String uername);
    List<Reminder> findAllByUserUsernameOrderByDateDesc(String username);

}
