package com.demo.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.demo.web.back.sys.entity.User;






public class SessionUser {


	public SessionUser() {
	}

	public static User getShiroUser() {
		Subject user = SecurityUtils.getSubject();
		if(user==null){
			return null;
		}
		User shiroUser = (User) user.getPrincipal();
		if(shiroUser==null){
			return null;
		}
		return shiroUser;
	}
	
	public static Session getSession(){
	 Subject user = SecurityUtils.getSubject();
		if(user==null){
			return null;
		}
		Session session = user.getSession();
		return session;
	} 
	


	public static  Long getUserId() {
	    User shiroUser=getShiroUser();
		if(shiroUser==null){
			return null;
		}
		return shiroUser.getId();
	}

	 

	public static String getUserName() {
	    User shiroUser=getShiroUser();
		if(shiroUser==null){
			return null;
		}
		return shiroUser.getUsername();
	}

	 

}
