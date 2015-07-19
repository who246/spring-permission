package com.demo.web.back.download.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.demo.entity.BaseEntity;
import com.demo.web.back.download.enums.Pace;
import com.demo.web.back.download.enums.TaskType;

@Table(name="download_task")
@Entity
public class Task extends BaseEntity<Long> {
    @Column(length = 50)
    private  String name;// 任务名称
    @NotNull
    private String url;// 下载的url
    
    @Column(length = 50)
    private String username;// 下载的人
    
    @Column(length = 20)
    private String userSecond;// 用时
    
    @Column(length = 10)
    @Enumerated(value = EnumType.STRING)
    private TaskType type;
    @Column(length = 10)
    @Enumerated(value = EnumType.STRING)
    private Pace pace;//下载状态
    @Transient
    private Float percen;//下载百分比
    

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUserSecond() {
        return userSecond;
    }
    
    public void setUserSecond(String userSecond) {
        this.userSecond = userSecond;
    }
    
    public TaskType getType() {
        return type;
    }
    
    public void setType(TaskType type) {
        this.type = type;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Pace getPace() {
        return pace;
    }

    
    public void setPace(Pace pace) {
        this.pace = pace;
    }

    
    public Float getPercen() {
        return percen;
    }

    
    public void setPercen(Float percen) {
        this.percen = percen;
    }
    
}
