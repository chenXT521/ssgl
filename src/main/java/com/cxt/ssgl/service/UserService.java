package com.cxt.ssgl.service;
import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.mapper.UserMapper;
import com.cxt.ssgl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserService implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserInfo() {
        List<User> user=userMapper.findUserInfo();
        //User user=null;
        return user;
    }

    @Override
    public List<Department> getDepInfo() {
        List<Department> deps=userMapper.getDepInfo();
        return deps;
    }

    @Override
    public List<Map<String, Object>> getUserInfo(String name, String depID) {
        List<Map<String, Object>> list = userMapper.getUserInfo(name,depID);
        return list;
    }

}
