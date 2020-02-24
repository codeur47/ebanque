package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.Role;

/**
 * RoleService
 */
public interface RoleService {
    Role findRoleByName(String name);
}