package com.demo.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.web.back.sys.entity.Menu;
import com.demo.web.back.sys.enums.MenuType;
import com.demo.web.back.sys.info.Tree;


public class TreeUtils {
    public static Tree<Long> menu2Tree(Menu menu){
        Tree<Long> tree = new Tree<Long>();
        tree.setId( menu.getId());
        tree.setpId( menu.getPid());
        tree.setName( menu.getMenuName());
        if(menu.getType()==null){
            tree.setRoot(true);
        }
        else if(menu.getType() .equals( MenuType.AUTH)){
            tree.setParent( false);
        }
        
        return tree;
    }
    public static List<Tree<Long>> menus2Trees(List<Menu> menus){
        List<Tree<Long>> trees = new ArrayList<Tree<Long>>();
        for (Menu menu : menus) {
            trees.add(menu2Tree(menu));
       }
        return trees;
    }
}
