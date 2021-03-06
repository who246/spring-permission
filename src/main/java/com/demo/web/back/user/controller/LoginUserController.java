package com.demo.web.back.user.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.common.utils.SessionUser;
import com.demo.controller.UtilsController;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.service.UserService;
import com.demo.web.back.user.info.PasswordInfo;

@Controller
@RequestMapping("/admin/sys/user/common")
public class LoginUserController extends UtilsController  {
    @Autowired
    private UserService userService;
    private String baseFile = "admin/sys/user";
    @RequestMapping(value="/changePassword", method = RequestMethod.GET)
    public String showChangeView(){
        
        return baseFile+"/userinfo/changePasswordForm";
    }
    @RequestMapping(value="/changePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("m") PasswordInfo info,BindingResult error,RedirectAttributes redirectAttributes){
        if(!info.getNewPassword1().equals(info.getNewPassword2())){
            error.rejectValue("newPassword2", "sys.user.pwdnoteq","两次密码输入不相同！！");
        }
       User user = SessionUser.getShiroUser();
       if(!userService.encodePassword(info.getOldPassword(),  user.getSalt()).equals(user.getPassword())){
           error.rejectValue("oldPassword", "sys.user.pwderror","密码错误！！");
       }
       if(!error.hasErrors()){
           user.setPassword(info.getNewPassword1());
           userService.changePassword(user);
           redirectAttributes.addFlashAttribute(Constants.MESSAGE, "修改成功");
           SecurityUtils.getSubject().logout();//退出
           return redirect("/login");//返回首页
       }
        return showChangeView();
    }
}
