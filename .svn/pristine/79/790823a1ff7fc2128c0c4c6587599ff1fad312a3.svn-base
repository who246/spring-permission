package com.demo.web.back.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.common.validate.ValidateResponse;
import com.demo.controller.BaseController;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.service.RoleService;
import com.demo.web.back.sys.service.UserService;


@Controller
@RequestMapping(value = "/admin/sys/user")
public class UserController extends BaseController<User, Long> {
    @Autowired
    private RoleService roleService;
    private String baseFile = "admin/sys/user";
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(@RequestParam("id") Long id,Model model){
        User user = getBaseService(UserService.class).findOne(id);
        model.addAttribute("m", user);
        List<Role> roles = roleService.findAll();
        roles.removeAll(user.getRoles());
        model.addAttribute("roleList", roles);
        return  getBaseFile() + "/auth";
    }
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam("roleIds")Long[] roleIds ,User user, RedirectAttributes redirectAttributes){
        try {
            getBaseService(UserService.class).auth(roleIds,user);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "授权角色成功成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.ERROR, e.getMessage());
        }
        return redirect(defaultViewPrefix()+getIndexPage());
    }
    // 添加
    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid  User user, RedirectAttributes redirectAttributes) {
        try {
            getBaseService(UserService.class).create(user);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "添加用户成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.ERROR, e.getMessage());
        }
        return redirect(defaultViewPrefix()+getIndexPage());
    }
    
   
    
    @RequestMapping(value = "validate", method = RequestMethod.GET)
    @ResponseBody
    public Object validate(@RequestParam("fieldId") String fieldId, @RequestParam("fieldValue") String fieldValue,
        @RequestParam(value = "id", required = false) Long id) {
        
        ValidateResponse response = ValidateResponse.newInstance();
        
        response.validateSuccess(fieldId, "");
        return response.result();
    }
    
    
    
    @Override
    public String getBaseFile() {
        return baseFile;
    }
}
