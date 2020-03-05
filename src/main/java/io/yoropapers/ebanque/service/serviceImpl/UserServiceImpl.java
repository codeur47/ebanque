package io.yoropapers.ebanque.service.serviceImpl;

import java.util.Set;

import javax.transaction.Transactional;
import java.util.Date;

import io.yoropapers.ebanque.model.MyTasks;
import io.yoropapers.ebanque.service.MyTasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.yoropapers.ebanque.dao.RoleDao;
import io.yoropapers.ebanque.dao.UserDao;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.model.UserRole;
import io.yoropapers.ebanque.service.AccountService;
import io.yoropapers.ebanque.service.UserService;

/**
 * UserServiceImpl
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder; 
    private AccountService accountService;
    private MyTasksService myTasksService;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, RoleDao roleDao, 
                          @Lazy AccountService accountService, MyTasksService myTasksService) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleDao = roleDao;
        this.accountService = accountService;
        this.myTasksService = myTasksService;
    }


    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userDao.existsUserByUsername(username);
    }

    @Override
    public boolean existsUsersByEmail(String email) {
        return userDao.existsUsersByEmail(email);
    }

    @Override
    public boolean existsUsersByUsernameAndEmail(String username, String email) {
        return userDao.existsUsersByUsernameAndEmail(username, email);
    }

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
        if(findUserByUsername(user.getLastName()) != null) LOG.info("Utilisateur avec le nom utilisateur {} existe déjà. ", user.getUsername());
        else {
           user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
           user.setCreatedAt(new Date());
           user.setUpdateAt(new Date());
           userRoles.forEach(ur -> {
               roleDao.save(ur.getRole());
           });
           user.getUserRoles().addAll(userRoles);
           user.setPrimaryAccount(accountService.createPrimaryAccount(user));
           user.setSavingsAccount(accountService.createSavingsAccount(user));
        }
        User userSave = userDao.save(user);
        myTasksService.saveMyTask(new MyTasks(new Date(), "Creation de compte","Vous avez créer votre compte utilisateur sur la plateforme. Cette opération crée automatiquement deux comptes : Un compte Courant et un compte Epargne. Merci de choisir Ebanque.",userSave));
        return userSave;
    }

    @Override
    public User findUserByPrimaryAccountAccountNumber(String accountNumber) {
        return userDao.findUserByPrimaryAccountAccountNumber(accountNumber);
    }

    @Override
    public User findUserBySavingsAccountAccountNumber(String accountNumber) {
        return userDao.findUserBySavingsAccountAccountNumber(accountNumber);
    }


}