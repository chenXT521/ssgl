package com.cxt.ssgl.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseMenu {
    private String uuid;
    private String menu_name;
    private int menu_level;
    private String menu_fid;
    private int memu_state;
    private String menu_url;
    private int menu_sort;
    private String menu_icon;
    private List<BaseMenu> children=new ArrayList<BaseMenu>();


    public List<BaseMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BaseMenu> children) {
        this.children = children;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_level() {
        return menu_level;
    }

    public void setMenu_level(int menu_level) {
        this.menu_level = menu_level;
    }

    public String getMenu_fid() {
        return menu_fid;
    }

    public void setMenu_fid(String menu_fid) {
        this.menu_fid = menu_fid;
    }

    public Integer getMemu_state() {
        return memu_state;
    }

    public void setMemu_state(Integer memu_state) {
        this.memu_state = memu_state;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public int getMenu_sort() {
        return menu_sort;
    }

    public void setMenu_sort(int menu_sort) {
        this.menu_sort = menu_sort;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }
}
