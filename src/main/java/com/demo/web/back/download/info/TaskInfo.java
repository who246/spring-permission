package com.demo.web.back.download.info;

import com.demo.web.back.download.enums.Pace;


public class TaskInfo{
   
    private Long id;
    private Float percen;//下载百分比
    private String userSecond;// 用时
    private Pace pace;//下载状态
    private String paceInfo;
    private String url;
    
  
 
 

    
    
    
    public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getPaceInfo() {
        return paceInfo;
    }

    public void setPaceInfo(String paceInfo) {
        this.paceInfo = paceInfo;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Float getPercen() {
        return percen;
    }
    
    public void setPercen(Float percen) {
        this.percen = percen;
    }
    
    public String getUserSecond() {
        return userSecond;
    }
    
    public void setUserSecond(String userSecond) {
        this.userSecond = userSecond;
    }
    
    public Pace getPace() {
        return pace;
    }
    
    public void setPace(Pace pace) {
        this.pace = pace;
        if(pace != null)
        setPaceInfo(pace.getInfo());
    }



    
}
