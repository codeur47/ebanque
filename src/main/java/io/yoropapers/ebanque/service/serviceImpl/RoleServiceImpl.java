package io.yoropapers.ebanque.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.yoropapers.ebanque.dao.RoleDao;
import io.yoropapers.ebanque.model.Role;
import io.yoropapers.ebanque.service.RoleService;

/**
 * RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

}