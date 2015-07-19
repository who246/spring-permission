package com.demo.web.back.download.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import com.demo.common.utils.SpringUtils;
import com.demo.web.back.download.config.DownLoadConfig;
import com.demo.web.back.download.entity.File;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.info.TaskInfo;


public class YoutubeExcutor  implements TaskExcutor  {
   
    private boolean isdelete = false;
    private TaskInfo info;
    private Object obj = new Object();
     
    public YoutubeExcutor(TaskInfo info) {
        super();
       
        this.info = info;
    }
   

     


    @Override
    public Object key() {
        return info.getId();
    }


    @Override
    public synchronized void state(Pace pace) {
        info.setPace(pace);
        
    }


    @Override
    public synchronized Pace state() {
        return info.getPace();
    }


    @Override
    public  void percen(Float percen) {
        synchronized (obj) {
            info.setPercen(percen);
        }
        
    }


    @Override
    public Float percen() {
        synchronized (obj) {
            return info.getPercen();
        }
    }


    
     


    @Override
    public TaskInfo read() {
        TaskInfo dInfo = new TaskInfo();
        BeanUtils.copyProperties(info, dInfo);
        return dInfo;
    }





    @Override
    public void delete() {
        isdelete = true;
        
    }
    @Override
    public void excute(){
        YoutubeSerive serive = SpringUtils.getBean(YoutubeSerive.class);
        FileService fileService = SpringUtils.getBean(FileService.class);
        DownLoadConfig config = SpringUtils.getBean(DownLoadConfig.class);
        StopWatch clock = new StopWatch(); 
        URL url;
        InputStream input = null;
        OutputStream stream = null;
        try {
            clock.start();
            state(Pace.loading); 
            serive.updatePace(Pace.loading,(Long)key());
            if(fileService.findByUrl(info.getUrl().trim()) == null){
            url = new URL(info.getUrl().trim());
            URLConnection con = url.openConnection();
            con.setConnectTimeout(3000);
             input =  con.getInputStream();
             String fix = paserUrlPostfix(info.getUrl().trim());
             StringBuilder path = new StringBuilder();
             path.append(config.savePath).append(java.io.File.separatorChar).append(new Date().getTime())
             .append(".").append(fix);
             stream = new FileOutputStream(path.toString());
           int max =  con.getContentLength();
           byte[] buffer = new byte[1024];
           int c=0;
            int i=0;
           while((c=input.read(buffer))!= -1){
               if(state().equals(Pace.stop) || isdelete){
                   System.out.println("任务停止："+key());
                  
                   return;
               }
      
               stream.write(buffer,0,c);
               i = c+i;
               percen((i*100f/max));
               
           }
           stream.flush();
           File myfile = new File();
           myfile.setPath(path.toString());
           myfile.setUrl(info.getUrl().trim());
           fileService.save(myfile);
           }else{
               percen(100f);
           }
           clock.stop();
           state(Pace.finish);
           isdelete = true;
           info.setUserSecond(clock.getTotalTimeSeconds()+"秒");
           serive.updatePaceAndUserSecond(Pace.finish,(Long)key(),info.getUserSecond());
           
        } catch (IOException e) {
            e.printStackTrace();
            info.setPace(Pace.fail);
            serive.updatePace(Pace.fail,(Long)key());
        }  finally{
            
            try {
                if(input !=null)
                input.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
            try {
                if(stream !=null)
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    private String paserUrlPostfix(String url){
        Assert.notNull(url, "url must not be null");
        int j = url.lastIndexOf("/");
        if(j == -1) return "";
        String f1 = url.substring(j,url.length());
        int i = f1.lastIndexOf(".");
        if(i == -1) return "";
        return f1.substring(i+1, f1.length());
    }
//    public static void main(String[] args) {        
//       String f = "http://down.txt99.cc/2%2F4%2F%CD%F2%B9%C5%CE%E4%D7%F0";
//       String f1 = f.substring(f.lastIndexOf("/"),f.length());
//        int i = f1.lastIndexOf(".");
//        System.out.println(i);
//       System.out.println(f1.substring(i+1, f1.length()));
//    }
}
