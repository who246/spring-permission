package com.test.sys;

import java.util.Set;





import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.WebAppConfig;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.service.MenuService;
import com.demo.web.back.sys.service.RoleService;

@SpringApplicationConfiguration(classes = WebAppConfig.class) 
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@ActiveProfiles("junit")
public class RoleTest {
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    @Test
    public void update()throws InterruptedException{
        Role role = new Role();
        role.setId(17l);
        Set<Menu> menus = role.getMenus();
        role.getMenus().clear();
        Long menuIds[] = {17l,18l};
       for (Long menuId : menuIds) {
         Menu menu = menuService.findOne(menuId);
       //  menu.setId(menuId);
         menus.add(menu);
       }
       roleService.update(role);  
    }
    @Test
    public void save()throws InterruptedException{
       
        
    }  
    
}
