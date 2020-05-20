package com.cxt.ssgl.controller;

import com.cxt.ssgl.entity.BaseMenu;
import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.entity.User;
import com.cxt.ssgl.mapper.UserMapper;
import com.cxt.ssgl.service.IndexService;
import com.cxt.ssgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    @RequestMapping("/login")
    public String login(HttpSession session){
        List<Department> deps = userService.getDepInfo();
        session.setAttribute("deps",deps);
        System.out.println(session.getAttribute("user"));
        return "login/login";
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public Map<String,Object> toLogin(@RequestBody Map<String, Object> params, User user,HttpSession session){
        String userName = params.get("userName").toString();
        String password = params.get("password").toString();
        String depId = params.get("depId").toString();
        List<Map<String,Object>> list = userService.getUserInfo(userName,depId);
        Map<String, Object> result = new HashMap<String, Object>();
        if(list.size()==0){
            result.put("isSuccess",false);
            result.put("returnValue","用户不存在");
        }else{
            Map<String, Object> userMap = list.get(0);
            String realPassword = userMap.get("password").toString();
            if(password.equals(realPassword)){
                result.put("isSuccess",true);
                result.put("returnValue","登录成功");
                user.setName(userMap.get("name").toString());
                user.setAge(Integer.parseInt(userMap.get("age").toString()));
                user.setDepID(userMap.get("depID").toString());
                user.setUserID(userMap.get("userID").toString());
                user.setDepName(userMap.get("depName").toString());
                user.setSex(userMap.get("sex").toString());
                user.setTel(userMap.get("tel").toString());
                session.setAttribute("user",user);
            }else{
                result.put("isSuccess",false);
                result.put("returnValue","密码错误");
            }
        }
        return result;
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index/index";
    }

    @RequestMapping("/getLeftMenu")
    @ResponseBody
    public List<BaseMenu> getLeftMenu(HttpSession session){
        User user = (User) session.getAttribute("user");
        String depID = user.getDepID();
        List<BaseMenu> menus = indexService.getLeftMenu(depID);
        Map<String, BaseMenu> map = new HashMap<String, BaseMenu>();
        List<BaseMenu> list = new ArrayList<BaseMenu>();
        for (BaseMenu menu : menus) {
            if (menu.getMenu_level() == 1) {
                map.put(menu.getUuid(), menu);
                list.add(menu);
            }
        }
        for (BaseMenu menu : menus) {
            if (menu.getMenu_level() == 2) {
                if (map.containsKey(menu.getMenu_fid())) {
                    map.get(menu.getMenu_fid()).getChildren().add(menu);
                }
            }
        }
        return list;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        try {
            request.getRequestDispatcher("/login").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
