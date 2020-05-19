package com.cxt.ssgl.controller;

import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/login")
    public String login(HttpSession session, Model model){
        List<Department> deps = userMapper.getDepInfo();
        session.setAttribute("deps",deps);
        return "login/login";
    }
}
