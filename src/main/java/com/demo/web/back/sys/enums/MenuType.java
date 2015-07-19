package com.demo.web.back.sys.enums;


public enum MenuType {
    AUTH("权限"),NAV("导航");
    private String info;

    private MenuType(String info) {
        this.info = info;
    }

    
    public String getInfo() {
        return info;
    }

    
    public void setInfo(String info) {
        this.info = info;
    }
    
}
