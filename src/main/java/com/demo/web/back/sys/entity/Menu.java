package com.demo.web.back.sys.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.demo.entity.BaseEntity;
import com.demo.web.back.sys.enums.MenuType;



@Entity
@Table(name = "sys_menu")
public class Menu extends BaseEntity<Long> implements Comparable<Menu>{
    
    
    
    @Transient
    private Set<Menu> children;
    
    @Column(name = "menu_name", length = 50)
    private String menuName;
    
    private String icon;
    // 父类id
    private Long pid;
    
    private String description;
    
    private String url;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private MenuType type;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "sys_role_menu", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @OrderBy("id ASC")
    private Set<Role> roles = new HashSet<Role>();
    
    
    public Set<Role> getRoles() {
        return roles;
    }

    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getMenuName() {
        return menuName;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public Long getPid() {
        return pid;
    }
    
    public void setPid(Long pid) {
        this.pid = pid;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
 
    
    
    
    public MenuType getType() {
        return type;
    }

    
    public void setType(MenuType type) {
        this.type = type;
    }

    public void addChildren(Menu m){
        if(children == null) children= new TreeSet<Menu>();
        children.add(m);
    }
    public Set<Menu> getChildren() {
        if(children == null) children= new TreeSet<Menu>();
        return children;
    }
    public boolean isHasChildren() {
        return (children != null && !children.isEmpty());
    }
    
    public void setChildren(Set<Menu> children) {
        this.children = children;
    }
    
    
    public String getIcon() {
        return icon;
    }

    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString(){
        return "Menu{" +
                "id=" +getId() +
                ", name='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public int compareTo(Menu o) {
        if(o.getId() > getId())
            return -1;
        else if(o.getId() < getId()){
            return 1;
        }
        return 0;
    }
}
