package com.demo.web.back.sys.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.demo.common.Constants;
import com.demo.service.BaseService;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.Notice;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.enums.MenuType;
import com.demo.web.back.sys.repository.MenuRepository;

@Service
public class MenuService extends BaseService<Menu, Long> {
     @Autowired
     private UserService userService;
    public MenuRepository getDao(){
        return getBaseRepository(MenuRepository.class);
    }
 
    
    public Set<Menu> getUserMenus(User user) {
       User loginUser = userService.findOne(user.getId());
       Set<Role> roles = loginUser.getRoles();
       Set<Menu> menus = new HashSet<Menu>();
       for (Role role : roles) {
           menus.addAll(role.getMenus());
      }
       Set<Menu> root = new TreeSet<Menu>();
       paser2Tree(menus,root , getRootMenu().getId());
       
        return root;
    }
    private void paser2Tree(Set<Menu> menus,Set<Menu>tree , Long pid){
        for (Menu menu : menus) {
            if(menu.getType() == null||!menu.getType().equals(MenuType.NAV)){
                continue;
            }
            if(menu.getPid().equals(pid)){
               if(!tree.contains(menu)){
                tree.add(menu);
               paser2Tree(menus,menu.getChildren() , menu.getId());
               }
            }
        }
    }
    public List<Menu> findMenuByPid(Long pid){
        return getDao().findByPid(pid);
    }
    public Menu getRootMenu(){
        return getDao().findByType(null).get(0);
    }
    public Set<String> menus2PermissStr(Set<Menu> menus) {
       Set<String> strList = new  HashSet<String>();
        for (Menu menu : menus) {
            if(StringUtils.isEmpty(menu.getUrl()) || !menu.getType().equals(MenuType.AUTH)) continue;
            strList.add(menu.getUrl());
        }
        return strList;
    }
    //缓存树
    @Override
    @Cacheable(value = Constants.DM_CacheKey, key = "'findAllMenu'")
    public List<Menu> findAll() {
        return super.findAll();
    }
    @Override
    @Caching(  
        evict = {  
    @CacheEvict(value = Constants.DM_CacheKey, key = "'findAllMenu'",beforeInvocation=true)
        }
        )
   public void update(Menu o) {
     super.update(o);
    }
    @Override
   @CacheEvict(value = Constants.DM_CacheKey, key = "'findAllMenu'",beforeInvocation=true)
   public void delete(Long id) {
     super.delete(id);
    }
}
