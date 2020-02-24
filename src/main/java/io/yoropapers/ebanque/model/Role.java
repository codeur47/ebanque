package io.yoropapers.ebanque.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Role
 */
@Entity
public class Role {

    @Id
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();


    public Role() {
    }

    public Role(int roleId, String name, Set<UserRole> userRoles) {
        this.roleId = roleId;
        this.name = name;
        this.userRoles = userRoles;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Role roleId(int roleId) {
        this.roleId = roleId;
        return this;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public Role userRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " roleId='" + getRoleId() + "'" +
            ", name='" + getName() + "'" +
            ", userRoles='" + getUserRoles() + "'" +
            "}";
    }


}