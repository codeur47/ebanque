package io.yoropapers.ebanque.dao;

import io.yoropapers.ebanque.model.MyTasks;
import io.yoropapers.ebanque.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyTasksDao extends JpaRepository<MyTasks, Long> {

    List<MyTasks> findAllByUserUsername(String username);
    List<MyTasks> findAll();
    List<MyTasks> findAllByUserUsernameOrderByDateAsc(String uername);
    List<MyTasks> findAllByUserUsernameOrderByDateDesc(String username);

}
