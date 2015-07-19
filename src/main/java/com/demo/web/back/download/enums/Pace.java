package com.demo.web.back.download.enums;

public enum Pace {
    stop("停止"), loading("正在下载"), finish("完成"),fail("失败");
    
    private String info;
    
    private Pace(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
}
