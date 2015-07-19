package com.demo.application.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@EnableCaching
@Configuration
//echcath缓存
@Profile("CacheConfig")
public class CacheConfig {
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
      return new EhCacheCacheManager (bean.getObject ());
    }
    @Bean  
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
       
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource ("ehcache-app.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
      }

}
