package com.jzqh.account;

import lombok.Data;

import java.util.Map;
import java.util.TreeSet;

@Data
public class MenuBo implements Comparable<MenuBo>{
    private String path;
    private String component;
    private String redirect;
    private TreeSet<MenuBo> children;
    private Map<String, String> meta;
    
 
    
    @Override
    public int compareTo(MenuBo o) {
        return o.getPath().compareTo(this.getPath());
    }
}
