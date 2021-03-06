package com.demo.web.back.sys.info;


/**
 * 构建树
 * 
 * 
 * 
 * 池超凡
 * 
 * 2015年5月22日 下午3:09:19
 * 
 * @version 1.0.0
 *
 */
public class Tree<ID>  {
 private ID id;
 private ID pId;
 private String name;
 private String iconSkin;
 private boolean isRoot = false;
 private boolean isParent= true;

public ID getId() {
    return id;
}

public void setId(ID id) {
    this.id = id;
}

 


public ID getpId() {
    return pId;
}


public void setpId(ID pId) {
    this.pId = pId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getIconSkin() {
    if(iconSkin != null){
        return iconSkin;
    }
    if(isRoot){
        return getDfRootIcon();
    }
    if(isParent){
        return getDfParentIcon();
    }
    
    return  getDfIcon();
}

private String getDfIcon() {
    // TODO Auto-generated method stub
    return "ztree_file";
}

private String getDfParentIcon() {
    // TODO Auto-generated method stub
    return "ztree_folder";
}

private String getDfRootIcon() {
    return "ztree_setting";
}

public void setIconSkin(String iconSkin) {
    this.iconSkin = iconSkin;
}

public boolean isRoot() {
    return isRoot;
}

public void setRoot(boolean isRoot) {
    this.isRoot = isRoot;
}

public boolean isIsParent() {
    return isParent;
}


public void setParent(boolean isParent) {
    this.isParent = isParent;
}

 

}

