package com.demo.web.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.common.Constants;



@Controller
public class LoginController {
    
 @RequestMapping(value="/login",method=RequestMethod.GET)
 public String showLogin(){
     return"front/login";
 }
 @RequestMapping(value="/login",method=RequestMethod.POST)
 public String login(HttpServletRequest request, ModelMap model){
     AuthenticationException shiroLoginFailureEx =
             (AuthenticationException) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
     if (shiroLoginFailureEx != null) {
         model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
     }
     return"front/login";
 }
 
}
