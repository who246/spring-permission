package com.demo.web.back.sys.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.demo.entity.BaseEntity;

@Entity
@Table(name="sys_role")
public class Role extends BaseEntity<Long> {
    @Column(name="role_name",length=50)
    private String roleName;
    @Column(name="role_code",length=50)
    private String roleCode;
    //描述
    private String description;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "sys_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    @OrderBy("id ASC")
    private Set<Menu> menus = new HashSet<Menu>();
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "sys_user_role",joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    @OrderBy("id ASC")
    private Set<User> users = new HashSet<User>();
    
    
    public Set<User> getUsers() {
        return users;
    }


    
    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public Set<Menu> getMenus() {
        return menus;
    }

    
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
