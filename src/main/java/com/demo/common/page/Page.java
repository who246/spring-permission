package com.demo.common.page;

public class Page {
    
    int pn = 1;
    
    int size = 10;
    
    String sort;
    
    String direction;
    
    public int getSize() {
        return size;
    }
    
    public String getSort() {
        return sort;
    }
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public int getPn() {
        return pn;
    }
    
    public void setPn(int pn) {
        this.pn = pn;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public void setSort(String sort) {
        this.sort = sort;
    }
}
