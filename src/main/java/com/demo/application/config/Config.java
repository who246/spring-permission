package com.demo.application.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demo.common.interceptor.CommonInterceptor;
/**
 * 
 * 
 * 系统一些通用配置的初始化
 * 
 * 
 * 池超凡
 * 
 * 2015年6月4日 上午1:01:13
 * 
 * @version 1.0.0
 *
 */
@Configuration
public class Config {
    //添加spring过滤器
     @Bean(name = "webMvcConfigurerAdapter")
     public WebMvcConfigurerAdapter getWebMvcConfigurerAdapter(){
         WebMvcConfigurerAdapter w = new WebMvcConfigurerAdapter() {
             public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                 
             }
             public void addInterceptors(InterceptorRegistry registry){  
                 
                 registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**");
                
             } 
        };
        return w;
     }
     
}
