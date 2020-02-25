package io.yoropapers.ebanque.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.model.UserRole;

/**
 * UserDao
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>{

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    boolean existsUserByUsername(String username);
    boolean existsUsersByEmail(String email);
    boolean existsUsersByUsernameAndEmail(String username, String email);
    User findUserByPrimaryAccountAccountNumber(String accountNumber);
    User findUserBySavingsAccountAccountNumber(String accountNumber);

}