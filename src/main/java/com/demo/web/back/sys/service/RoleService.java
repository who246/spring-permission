package com.demo.web.back.sys.service;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.service.BaseService;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.repository.RoleRepository;


@Service
public class RoleService extends BaseService<Role, Long> {
    @Autowired
    private MenuService menuService;
    
    public RoleRepository getDao() {
        return getBaseRepository(RoleRepository.class);
    }

    public void auth(String menuIdStr, Role role) {
        Role role2 = findOne(role.getId());
        Set<Menu> menus = role2.getMenus();
        role2.getMenus().clear();
        if(StringUtils.isEmpty(menuIdStr)){
           super.update(role2);
            return;
        }
        String [] ids = menuIdStr.split(",");
       for (String id : ids) {
         Menu menu = new Menu();//menuService.findOne(menuId);
         menu.setId(Long.valueOf(id));
         menus.add(menu);
       }
       super.update(role2);
    }
    //更新基本信息
    @Override 
    public void update(Role o) {
        Role r = getDao().findOne(o.getId());
        BeanUtils.copyProperties(o, r,"users","menus");
    }

 
    
}
