package com.demo.web.back.download.enums;


public enum TaskType {
    youtube("youtube"),pan("网盘");
    private String info;
    private TaskType(String info) {
        this.info = info;
    }

    
    public String getInfo() {
        return info;
    }

    
    public void setInfo(String info) {
        this.info = info;
    }
}
