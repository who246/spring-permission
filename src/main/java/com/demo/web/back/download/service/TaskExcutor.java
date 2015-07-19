package com.demo.web.back.download.service;

import com.demo.web.back.download.enums.Pace;


public interface TaskExcutor {
    public Object key();
    public void state(Pace pace);
    public Pace state();
    public void percen(Float percen);
    public Float percen();
    
    public void excute();
    public Object read();
    public void delete();
}
