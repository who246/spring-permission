package com.demo.web.back.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.common.utils.TreeUtils;
import com.demo.controller.BaseController;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.Role;
import com.demo.web.back.sys.service.MenuService;
import com.demo.web.back.sys.service.RoleService;


@Controller
@RequestMapping(value = "/admin/sys/role")
public class RoleController extends BaseController<Role, Long> {
    @Autowired
    private MenuService menuService;
    private RoleService getService(){
        return getBaseService(RoleService.class);
    }
    private String baseFile = "admin/sys/role";
   
    
    private void authAndDetialsCommon(Long id,Model model){
        Role role = getService().findOne(id);
        model.addAttribute("m", role);
        model.addAttribute("trees", TreeUtils.menus2Trees(menuService.findAll()));
        StringBuilder builder = new StringBuilder();
        for (Menu menu : role.getMenus()) {
            builder.append(menu.getId()).append(",");
        }
        if(builder.length() > 0)
        builder.deleteCharAt(builder.length()-1);
        model.addAttribute("selectTreeIds", builder);
    }
    @Override
    public String detials(@RequestParam("id") Long id,Model model) {
        authAndDetialsCommon(id,model);
        model.addAttribute(Constants.OP_NAME, "查看");
        setCommon(model);
        return getBaseFile() + "/editForm";
    }
//   
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(@RequestParam("id") Long id,Model model){
        authAndDetialsCommon(id,model);
        model.addAttribute(Constants.OP_NAME, "授权");
        
        return  getBaseFile() + "/editForm";
    }
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam("menuIdStr") String menuIdStr ,Role role, RedirectAttributes redirectAttributes){
        
        try {
          //  baseService.save(m);
            getService().auth(menuIdStr,  role);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "授权成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.ERROR, e.getMessage());
        }
        
        return  redirect(defaultViewPrefix()+getAddRedirect());
    }

 
    @Override
    public String getBaseFile() {
        return baseFile;
    }
  
}
