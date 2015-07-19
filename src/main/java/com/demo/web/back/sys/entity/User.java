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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import com.demo.entity.BaseEntity;

@Entity
@Table(name="sys_user")
public class User extends BaseEntity<Long> {
    public static final String USERNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;
    //用户名
    @Column(length=50)
    @NotNull
    @Pattern(regexp = USERNAME_PATTERN)
    private String username;
    //密码
    @Column(length=50)
    @NotNull
    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    //加密种子
    private String salt;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "sys_user_role",joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @OrderBy("id ASC")
    private Set<Role> roles = new HashSet<Role>();
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }

    
    public Set<Role> getRoles() {
        return roles;
    }

    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
}
