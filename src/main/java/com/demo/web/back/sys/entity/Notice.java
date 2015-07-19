package com.demo.web.back.sys.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.demo.entity.BaseEntity;

@Entity
@Table(name = "sys_notice")
public class Notice extends BaseEntity<Long> {
    private String username ;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String info;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    
    
}
