 
package com.demo.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.exception.UserException;
import com.demo.web.back.sys.service.MenuService;
import com.demo.web.back.sys.service.UserService;

/**
 * 
 * 
 *  用户授权验证
 * 
 * 
 * 池超凡
 * 
 * 2015年5月13日 下午11:56:45
 * 
 * @version 1.0.0
 *
 */

@Component("shiroRealmImpl")
public class ShiroRealmImpl extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
 
   
	
    
    public MenuService getMenuService() {
        return menuService;
    }


    
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }


    public UserService getUserService() {
        return userService;
    }

    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
        User user =  (User) principals.getPrimaryPrincipal();
        User shiroUser = userService.findOne(user.getId());
        Set<Role> roles = shiroUser.getRoles();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            info.addRole(role.getRoleCode());
            info.addStringPermissions(menuService.menus2PermissStr(role.getMenus()));
        }
	    return info;
	}
    
	/**
	 * AuthenticationInfo represents a Subject's (aka user's) stored account
	 * information relevant to the authentication/log-in process only.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    
	    UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) token;
	    String username = usernamePasswordToke.getUsername().trim();
	    String password = new String (usernamePasswordToke.getPassword());
	    User user = null;
	    try{

	     user = userService.login(username,password);
	    }catch(UserException e){
	        throw new AuthenticationException(e.getMessage(),e);
	    }
	    
		return new SimpleAuthenticationInfo(user, usernamePasswordToke.getPassword(), getName());
	}



}
