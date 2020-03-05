package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.MyTasks;

import java.util.List;

public interface MyTasksService {

    void saveMyTask(MyTasks myTasks);
    List<MyTasks> findAllByUserUsername(String username);
    List<MyTasks> findAll();
    List<MyTasks> findAllByUserUsernameOrderByDateAsc(String uername);
    List<MyTasks> findAllByUserUsernameOrderByDateDesc(String username);

}
