package com.demo.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
/**
 * 
 * 
 * 使用拦截url进行权限的判断，使用注解比较难维护
 * 
 * 
 * 池超凡
 * 
 * 2015年5月1日 上午10:53:22
 * 
 * @version 1.0.0
 *
 */
 
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
   
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        int i=uri.indexOf(contextPath);
        if(i>-1){
            uri=uri.substring(i+contextPath.length());
        }
        if(StringUtils.isEmpty(uri)){
            uri="/";
        }
        int j=uri.lastIndexOf(".");
        if(j>-1){
            uri=uri.substring(0, j);
        }
        if(!subject.isPermitted(uri)){
            throw new UnauthorizedException("你没有权限！");  
        }
        return true;
    }
  
}
