package com.cxt.ssgl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userSetting")
public class UserSettingController {

    @RequestMapping("/addUser")
    public String toIndex(){
        return "userSetting/addUser";
    }
}
