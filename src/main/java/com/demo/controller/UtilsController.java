package com.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.common.Constants;
import com.demo.common.utils.ReflectUtils;

/**
 * 
 * 
 * Controller工具类
 * 
 * 
 * 池超凡
 * 
 * 2015年5月20日 下午11:45:23
 * 
 * @version 1.0.0
 *
 */
public  class UtilsController  {
 
  
    
    public String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        return currentViewPrefix;
    }
    public Class<Object> getModeltype() {
        return ReflectUtils.findParameterizedType(getClass(), 0);
    }
    
    public String redirect(String redirect) {
        
        return "redirect:" + redirect;
    }
    public Map<String, String> sendSucceed() {
 
        return sendMsg("操作成功");
    }
    public Map<String, String> sendMsg(String msg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.MESSAGE, msg);
        return map;
    }
    public  Map<String, String> transParameters(Map<String, Object> parames) {
        Map<String, String> map = new HashMap<String, String>();
        Set<String> sets = parames.keySet();
        for (String string : sets) {
            String str = parames.get(string).toString();
            //String v=  str.subSequence(str.lastIndexOf("_")+1, str.length()).toString();
            map.put(string.replaceAll("\\.", "_"), str);
        }
        return  map;
    }
     
}
