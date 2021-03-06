package com.demo.web.back.sys.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.application.config.WebConfig;
import com.demo.common.utils.SessionUser;
import com.demo.controller.BaseController;
import com.demo.web.back.sys.entity.Notice;
import com.demo.web.back.sys.service.NoticeService;
@Controller
@RequestMapping(value = "/admin/sys/notice")
public class NoticeController extends BaseController<Notice, Long> {
     private String baseFile = "/admin/sys/notice";
     private String index =  "/list?page.sort=createDate&page.direction=desc";
    @Autowired
    private WebConfig webConfig; 
    @Override
    public String getBaseFile() {
        return baseFile;
    }
    @Override
    public String getUpdateRedirect() {
        //返回根据时间排序排序
        return index;
    }
    @Override
    public String getAddRedirect() {
        //返回根据时间排序排序
        return index;
    }
    @Override
    public String getDeleteRedirect() {
        //返回根据时间排序排序
        return index;
    }
    @RequestMapping("/common/welcome")
    public  String welcome(Model model){
        Notice notice = getBaseService(NoticeService.class).getWelcomeNotice();
        if(notice != null)
        model.addAttribute("m", notice);
        if(webConfig!=null && StringUtils.isNotEmpty(webConfig.getWelcome()))
        model.addAttribute("welcome", webConfig.getWelcome().replace("{username}", SessionUser.getUserName()));
        return defView("/welcome");
    }
}
