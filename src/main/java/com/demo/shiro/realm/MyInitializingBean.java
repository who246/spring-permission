package com.demo.shiro.realm;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.shiro.config.ShiroConfiguration;

@Component
//初始化完成后做的事情
public class MyInitializingBean implements InitializingBean {
    @Autowired
   private  ShiroConfiguration scf ;
    @Autowired
    private  ShiroRealmImpl realm ;

    @Override
    public void afterPropertiesSet() throws Exception {
        scf.getDefaultWebSecurityManager().setRealm(realm);
        
    }
    
}
