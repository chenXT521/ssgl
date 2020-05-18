package com.cxt.ssgl.mapper;

import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findUserInfo();

    List<Department> getDepInfo();
}
