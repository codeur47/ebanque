package io.yoropapers.ebanque.service.serviceImpl;

import java.util.Set;

import javax.transaction.Transactional;
import java.util.Date;

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

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, RoleDao roleDao, 
                          @Lazy AccountService accountService) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleDao = roleDao;
        this.accountService = accountService;
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
        return userDao.save(user);
    }

    
}