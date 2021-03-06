package com.demo.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.demo.shiro.filter.MyFormAuthenticationFilter;
import com.demo.shiro.filter.MyPermissionsAuthorizationFilter;


@Configuration

public class ShiroConfiguration  {

	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
 

	@Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter getFormAuthenticationFilter() {
	    MyFormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();
 
        return formAuthenticationFilter;
    }
	@Bean(name = "permiss")
    public MyPermissionsAuthorizationFilter getMyPermissionsAuthorizationFilter() {
	    MyPermissionsAuthorizationFilter permiss = new MyPermissionsAuthorizationFilter();
 
        return permiss;
    }
	@Bean(name = "shiroEhcacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public MemoryConstrainedCacheManager getEhCacheManager() {
 
	    org.apache.shiro.cache.MemoryConstrainedCacheManager em = new org.apache.shiro.cache.MemoryConstrainedCacheManager();
 
		return em;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	@Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
	     MethodInvokingFactoryBean mi = new MethodInvokingFactoryBean();
	     DefaultWebSecurityManager[] os = {getDefaultWebSecurityManager()};
	     mi.setArguments(os);
	     mi.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
	     
        return mi;
    }
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
 
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		 //ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext();
		
		//dwsm.setRealm(getShiroRealm());
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getDefaultWebSecurityManager());
		
		return new AuthorizationAttributeSourceAdvisor();
	}

	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		shiroFilterFactoryBean
				.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/admin/index");	
		
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/**/validate", "anon");//数据验证不拦截
		filterChainDefinitionMap.put("/login", "authc");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/admin/index", "user");
		filterChainDefinitionMap.put("/admin/welcome", "user");
		filterChainDefinitionMap.put("/**/common/**", "user");//公用方法不拦截
		filterChainDefinitionMap.put("/", "user");
		
     //	filterChainDefinitionMap.put("/**", "user,permiss");
	      shiroFilterFactoryBean.getFilters().put("authc", getFormAuthenticationFilter());
	        shiroFilterFactoryBean.getFilters().put("permiss", getMyPermissionsAuthorizationFilter());
		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
		return shiroFilterFactoryBean;
	}

 

     

}
