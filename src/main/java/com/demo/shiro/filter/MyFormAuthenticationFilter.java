package com.demo.shiro.filter;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 
 * 
 *重写异常时信息设置
 *原本他的方法是把异常的类型名称放入
 *这里改成直接把异常 放入
 * 
 * 
 * 池超凡
 * 
 * 2015年5月3日 上午10:51:26
 * 
 * @version 1.0.0
 *
 */


public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
       
        
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
   
}
