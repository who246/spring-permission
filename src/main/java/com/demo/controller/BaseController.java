package com.demo.controller;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.common.page.PageDate;
import com.demo.common.utils.Servlets;
import com.demo.entity.BaseEntity;
import com.demo.service.BaseService;


public abstract class BaseController<M extends BaseEntity<ID>, ID extends Serializable> extends UtilsController  {
    private static final String DF_INDEX= "/list";
 
    public String getIndexPage() {
        return DF_INDEX;
    }
    public String getUpdateRedirect() {
        return DF_INDEX;
    }
    public String getDeleteRedirect() {
        return DF_INDEX;
    }
    public String getAddRedirect() {
        return DF_INDEX;
    }
    
    private BaseService<M, ID> baseService;
    
    public BaseService<M, ID> getBaseService() {
        return baseService;
    }
    
    public <T> T getBaseService(Class<T> t) {
        return (T) baseService;
    }
    @Autowired
    public void setBaseService(BaseService<M, ID> baseService) {
        this.baseService = baseService;
    }
    
    public abstract String getBaseFile();
    public String defView(String page){
        return getBaseFile() + page;
    }
    protected void setCommon(Model m){ }
    protected void setCommon(M m){ }
    // 显示列表
    @RequestMapping(value = {"/list"})
    public String list(ServletRequest request, PageDate page, Model model) {
        Map<String, Object> parames = Servlets.getParametersStartingWith(request);
        model.addAllAttributes(transParameters(parames));
        Page<M> pages = baseService.list(parames, page);
        model.addAttribute("page", pages);
        setCommon(model);
        return getBaseFile() + getIndexPage();
    }
    
    // 显示添加视图
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateView(Model m) throws InstantiationException, IllegalAccessException {
        m.addAttribute("m", getModeltype().newInstance());
        m.addAttribute(Constants.OP_NAME, "新增");
        setCommon(m);
        return getBaseFile() + "/editForm";
    }
    
    // 添加
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid M m, RedirectAttributes redirectAttributes) {
        try {
            setCommon(m);
            baseService.save(m);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "添加成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.ERROR, e.getMessage());
        }
        return redirect(defaultViewPrefix()+getAddRedirect());
    }
    
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "ids") ID[] ids, RedirectAttributes redirectAttributes) {
        baseService.deleteBatch(ids);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirect(defaultViewPrefix()+getDeleteRedirect());
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam(value = "id") ID id, Model model) {
        
        M m = baseService.findOne(id);
        
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "修改");
        setCommon(model);
        return getBaseFile() + "/editForm";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid M m, RedirectAttributes redirectAttributes) {
        try {
            setCommon(m);
            baseService.update(m);
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, "更新成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.ERROR, e.getMessage());
        }
        return redirect(defaultViewPrefix()+getUpdateRedirect());
    }
    
    @RequestMapping(value = "/detials", method = RequestMethod.GET)
    public String detials(@RequestParam(value = "id") ID id, Model model) {
        M m = baseService.findOne(id);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "查看");
        setCommon(model);
        return getBaseFile() + "/editForm";
    }
    
   
}
