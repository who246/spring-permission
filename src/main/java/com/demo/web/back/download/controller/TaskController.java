package com.demo.web.back.download.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.common.Constants;
import com.demo.controller.BaseController;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.info.TaskInfo;
import com.demo.web.back.download.service.TaskService;


public abstract class TaskController extends BaseController<Task, Long>{

    //开始任务
    @RequestMapping("/startTask")
    @ResponseBody
    public Map<String, String> startTask(@RequestParam(value = "ids") Long[] ids){
        getBaseService(TaskService.class).startTask(ids);
        return sendMsg("任务开始！！");        
    }
    @RequestMapping("/common/taskCheck")
    @ResponseBody
    public List<TaskInfo> taskCheck(@RequestParam(value = "ids") Long[] ids){
        return getBaseService(TaskService.class).taskCheck(ids);       
    }
    @Override
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "ids") Long[] ids, RedirectAttributes redirectAttributes) {
        getBaseService(TaskService.class).removeAll(ids);
        
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirect(defaultViewPrefix()+getDeleteRedirect());
    }
}
