package com.demo.web.back.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.common.utils.TreeUtils;
import com.demo.controller.BaseController;
import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.enums.MenuType;
import com.demo.web.back.sys.info.Tree;
import com.demo.web.back.sys.service.MenuService;
@Controller
@RequestMapping(value = "/admin/sys/menu")
public class MenuController extends BaseController<Menu,Long> {
    public MenuService getService() {
        return getBaseService(MenuService.class);
    }
    //覆盖更新后调至地址
    @Override
    public String getUpdateRedirect(){
        return "/common/success";
    }
    @Override
    public String getAddRedirect(){
        return "/common/success";
    }
    //文件目录前缀
    private String baseFile = "admin/sys/resource";
    @Override
    public String getBaseFile() {
      
        return baseFile;
    }
    @Override
     protected void setCommon(Model m) {
        m.addAttribute("TypeList", MenuType.values());
    };
    @RequestMapping("/common/success")
    public String success(){
        return baseFile+"/success";
    }
    @RequestMapping({"main"})
    public String main(){
        return baseFile+"/main";
    }
    @RequestMapping({"/showTree"})
    public String showTree(Model m){
        Menu  menu = getService().getRootMenu();
        List<Menu> menus = getService().findMenuByPid(menu.getId());
        menus.add(menu);
        m.addAttribute("trees", TreeUtils.menus2Trees(menus));
        
        return baseFile+"/tree";
    }
    
    @RequestMapping({"/common/treeAjax"})
    @ResponseBody
    public List<Tree<Long>> treeAjax(@RequestParam(value="id",defaultValue="-1") Long id){
        if(id.equals(-1l)){
            List<Tree<Long>> trees = new ArrayList<Tree<Long>>();
            trees.add(TreeUtils.menu2Tree(getService().getRootMenu()));
            return trees;
        }
        return  TreeUtils.menus2Trees(getService().findMenuByPid(id));
    }
    
    @RequestMapping(value = "/updateName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> update( Menu m) {
        getService().updateNotNull(m);
        return sendSucceed();
    }
    @RequestMapping(value = "/delete/Deprecated")
    @Override
    @Deprecated
    public String delete(@RequestParam(value = "ids") Long[] ids, RedirectAttributes redirectAttributes) {
        throw new RuntimeException("废弃方法!!");
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam(value = "ids") Long[] ids) {
        getService().deleteBatch(ids);
         return sendSucceed();
    }
    @RequestMapping(value = "/create/Deprecated", method = RequestMethod.GET)
    @Override
    @Deprecated
    public String showCreateView(Model m) throws InstantiationException, IllegalAccessException {
        throw new RuntimeException("废弃方法!!");
    }
    // 显示添加视图
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateView(Model m,Menu menu) throws InstantiationException, IllegalAccessException {
        m.addAttribute("m", menu);
        m.addAttribute(Constants.OP_NAME, "新增");
        setCommon(m);
        return getBaseFile() + "/editForm";
    }
    @RequestMapping(value = "/move")
    @ResponseBody
    public Map<String, String> move(Menu menu)  {
        getService().updateNotNull(menu);
        return sendSucceed();
    }
 
}
