package com.demo.web.back.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.common.utils.SessionUser;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.entity.User;
import com.demo.web.back.sys.service.MenuService;

@Controller
public class IndexController {
    @Autowired
    private MenuService menuService;
    @RequestMapping(value={"/",""},method=RequestMethod.GET)
    public String index(){
 
        return "redirect:admin/index";
    }
	@RequestMapping(value={ "/admin/index"},method=RequestMethod.GET)
	public String index(Model m){
	    User user = SessionUser.getShiroUser();
	    Set<Menu> menus = menuService.getUserMenus(user);
	    m.addAttribute("menus", menus);
		return "admin/index/index";
	}
//	@ResponseBody
//	@RequestMapping(value={ "/json"})
//    public  HashMap<String, String> json(){
//	    HashMap<String, String> map = new HashMap<String, String>();
//	    map.put("aa", "aaa");
//        return map;
//    }
//	@RequestMapping(value={ "/jsonAndHtml"})
//    public String jsonAndHtml(Model model){
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("aa", "aaa");
//	    model.addAllAttributes(map);
//        return "index.jsp";
//    }
//	
}
