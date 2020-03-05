package io.yoropapers.ebanque.service.serviceImpl;

import io.yoropapers.ebanque.dao.MyTasksDao;
import io.yoropapers.ebanque.model.MyTasks;
import io.yoropapers.ebanque.service.MyTasksService;
import io.yoropapers.ebanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyTasksServiceImpl implements MyTasksService {

    private MyTasksDao myTasksDao;

    @Autowired
    public MyTasksServiceImpl(MyTasksDao myTasksDao) {
        this.myTasksDao = myTasksDao;
    }

    @Override
    @Transactional
    public void saveMyTask(MyTasks myTasks) {
        this.myTasksDao.save(myTasks);
    }

    @Override
    public List<MyTasks> findAllByUserUsername(String username) {
        return myTasksDao.findAllByUserUsername(username);
    }

    @Override
    public List<MyTasks> findAll() {
        return myTasksDao.findAll();
    }

    @Override
    public List<MyTasks> findAllByUserUsernameOrderByDateAsc(String uername) {
        return myTasksDao.findAllByUserUsernameOrderByDateDesc(uername);
    }

    @Override
    public List<MyTasks> findAllByUserUsernameOrderByDateDesc(String username) {
        return myTasksDao.findAllByUserUsernameOrderByDateAsc(username);
    }
}
