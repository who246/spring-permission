package com.demo.web.back.download.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.demo.entity.BaseEntity;



@Table(name="File",
    uniqueConstraints={@UniqueConstraint(columnNames={"url"})})
@Entity
public class File extends BaseEntity<Long>{
   private String path;
   private String url;


public String getUrl() {
    return url;
}



public void setUrl(String url) {
    this.url = url;
}


public String getPath() {
    return path;
}


public void setPath(String path) {
    this.path = path;
}
   
}
