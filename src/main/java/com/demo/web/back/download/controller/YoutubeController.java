package com.demo.web.back.download.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.common.utils.DownloadUtils;
import com.demo.common.utils.SessionUser;
import com.demo.service.BaseService;
import com.demo.web.back.download.entity.File;
import com.demo.web.back.download.entity.Task;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.enums.TaskType;
import com.demo.web.back.download.service.FileService;
import com.demo.web.back.download.service.YoutubeSerive;

@Controller
@RequestMapping("/admin/download/youtube")
public class YoutubeController extends TaskController{
  private String baseFile = "admin/download/youtube";
   @Autowired
   private FileService fileService;
 

    @Override
    public String getBaseFile() {
        
        return baseFile;
    }
    @Override
    @Autowired @Qualifier("youtubeSerive")
    public void setBaseService(BaseService<Task, Long> baseService) {
        super.setBaseService(baseService);
    }
    @Override
        protected void setCommon(Task m) {
            m.setPace(Pace.stop);
            m.setType(TaskType.youtube);
            m.setUsername(SessionUser.getUserName());
            super.setCommon(m);
        }
    
       @RequestMapping("/download")
       public  void download(@RequestParam("id") Long id,HttpServletRequest request, HttpServletResponse response) throws IOException {
           Task task = getBaseService(YoutubeSerive.class).findOne(id);
           Assert.notNull(task, "任务不存在！");
           Assert.notNull(task.getUrl().trim(), "地址不存在！");
           File file = fileService.findByUrl(task.getUrl().trim());
           Assert.notNull(file, "任务未完成！");
           DownloadUtils.download(request, response, file.getPath(), "");
       }
//       @RequestMapping("/common/htmlTest")
//       @ResponseBody
//       public JSONPObject htmlTest(
//           @RequestParam("jsoncallback") String jsoncallback){
//           System.out.println("sendHtmlTest");
//           JSONPObject jsonpObject = new JSONPObject(jsoncallback, sendSucceed());
//           return jsonpObject;
//       }
}
