package com.test.sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.WebAppConfig;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.service.MenuService;

 

@SpringApplicationConfiguration(classes = WebAppConfig.class) 
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@ActiveProfiles("junit")
public class MenuTest {

    @Autowired
    private MenuService menuService;
    @Test
    public void findbyType() throws InterruptedException{
           
        menuService.getRootMenu();
    }
    @Test
    public void updateNotNull() throws InterruptedException{
        Menu o = new Menu();
        o.setId(6l);
        o.setMenuName("其他管理");
        o.setIcon("");
       menuService.updateNotNull(o);
 }
}
