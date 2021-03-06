package com.demo.web.back.sys.service;

import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.common.utils.Md5Utils;
import com.demo.service.BaseService;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.exception.UserException;
import com.demo.web.back.sys.repository.UserRepository;


@Service
public class UserService extends BaseService<User, Long> {
    
    public UserRepository getDao() {
        return getBaseRepository(UserRepository.class);
    }
    
    public void encodePassword(User user) {
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            new UserException("加密的原密码不能为空!!");
        }
        String enPassword = Md5Utils.hash(password  
            + StringUtils.defaultString(user.getSalt()));
        user.setPassword(enPassword);
    }
    
    public String encodePassword(String password,   String salt) {
        return Md5Utils.hash(password +StringUtils.defaultString(salt));
    }
    
    public User login(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            new UserException("用户名不能为空!!");
        } else if (StringUtils.isEmpty(password)) {
            new UserException("密码不能为空!!");
        }
        User user = getBaseRepository(UserRepository.class).findByUsername(username);
        if (user == null) {
            throw new UserException("用户名或密码错误!!");
        }
        User tmpUser = new User();
        tmpUser.setUsername(username);
        tmpUser.setPassword(password);
        tmpUser.setSalt(user.getSalt());
        encodePassword(tmpUser);
        
        if (!user.getPassword().equals(tmpUser.getPassword())) {
            throw new UserException("用户名或密码错误!!");
        }
        return user;
    }
    
    public void isExist(User user) {
        if (getDao().findByUsername(user.getUsername()) != null) {
            throw new UserException("用户已经存在");
        }
    }
    
    public void create(User user) {
        isExist(user);
        user.setSalt(UUID.randomUUID().toString());
        encodePassword(user);
        save(user);
    }
    //修改用户基本信息
    @Override
    public void update(User user) {
        User m = getDao().findOne(user.getId());
        BeanUtils.copyProperties(user, m, "roles");
    }
    //修改密码
    public void changePassword(User user) {
        User m = getDao().findOne(user.getId());        
         m.setPassword(user.getPassword());
        encodePassword(m);
        super.update(m);
    } 
   
    public void auth(Long[] roleIds, User o) {
     User user = getDao().findOne(o.getId());
     user.getRoles().clear();
     Set<Role> roles = user.getRoles();
        for (Long id : roleIds) {
            Role role = new Role();
            role.setId(id);
            roles.add(role);
        }
       super.update(user);
    }
    
}
