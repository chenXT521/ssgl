package com.cxt.ssgl.mapper;

import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findUserInfo();

    List<Department> getDepInfo();

    List<Map<String,Object>> getUserInfo(String name,String depID);
}
