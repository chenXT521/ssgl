package com.cxt.ssgl.service;

import com.cxt.ssgl.entity.BaseMenu;
import com.cxt.ssgl.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService implements IndexMapper {
    @Autowired
    private IndexMapper indexMapper;
    @Override
    public List<BaseMenu> getLeftMenu(String depID) {
        List<BaseMenu> list = indexMapper.getLeftMenu(depID);
        return list;
    }
}
