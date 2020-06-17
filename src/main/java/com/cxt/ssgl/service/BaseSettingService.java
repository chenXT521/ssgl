package com.cxt.ssgl.service;

import com.cxt.ssgl.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseSettingService implements BaseMapper {

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public int removeDepById(String depId) {
        return baseMapper.removeDepById(depId);
    }

    @Override
    public int removeRelDepMenu(String depId) {
        return baseMapper.removeRelDepMenu(depId);
    }

    @Override
    public List<Map<String, Object>> getDepByName(String depName) {
        return baseMapper.getDepByName(depName);
    }

    @Override
    public int addDep(Map<String, Object> map) {
        return baseMapper.addDep(map);
    }

    @Override
    public int editDep(Map<String, Object> map) {
        return baseMapper.editDep(map);
    }

}
