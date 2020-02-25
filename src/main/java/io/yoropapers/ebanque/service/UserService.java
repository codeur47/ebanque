package io.yoropapers.ebanque.service;

import java.util.Set;

import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.model.UserRole;

/**
 * UserService
 */
public interface UserService {

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    boolean existsUserByUsername(String username);
    boolean existsUsersByEmail(String email);
    boolean existsUsersByUsernameAndEmail(String username, String email);
    User createUser(User user, Set<UserRole> userRoles);
    User findUserByPrimaryAccountAccountNumber(String accountNumber);
    User findUserBySavingsAccountAccountNumber(String accountNumber);
    
}