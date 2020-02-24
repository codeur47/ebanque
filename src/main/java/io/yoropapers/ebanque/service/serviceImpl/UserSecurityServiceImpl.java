package io.yoropapers.ebanque.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.yoropapers.ebanque.dao.UserDao;
import io.yoropapers.ebanque.model.User;

/**
 * UserSecurityServiceImpl
 */
@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public UserSecurityServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if(null == user) {
            LOG.warn("Nom Utilisateur {} introuvable", username);
            throw new UsernameNotFoundException("Nom Utilisateur "+ username + " introuvable");
        } 
        return user;
    }
}