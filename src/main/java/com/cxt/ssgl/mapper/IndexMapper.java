package com.cxt.ssgl.mapper;

import com.cxt.ssgl.entity.BaseMenu;

import java.util.List;

public interface IndexMapper {
    List<BaseMenu> getLeftMenu(String depID);
}
