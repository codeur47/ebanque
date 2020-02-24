package io.yoropapers.ebanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.yoropapers.ebanque.model.Role;

/**
 * RoleDao
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}