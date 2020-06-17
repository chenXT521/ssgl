package com.cxt.ssgl.controller;


import com.cxt.ssgl.entity.Department;
import com.cxt.ssgl.service.BaseSettingService;
import com.cxt.ssgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/baseSetting")
public class BaseSettingController {
    @Autowired
    private BaseSettingService baseSettingService;
    @Autowired
    private UserService userService;

    @RequestMapping("/depSetting")
    public String toDepSetting(){
        return "baseSetting/depSetting";
    }

    @RequestMapping("/professionSetting")
    public String professionSetting(){
        return "baseSetting/professionSetting";
    }

    @RequestMapping("/getDeps")
    @ResponseBody
    public List<Department> getDeps(HttpSession session){
        List<Department> deps = (List<Department>) session.getAttribute("deps");
        System.out.println(deps);
        return deps;
    }

    @RequestMapping("/removeDepById")
    @ResponseBody
    public String removeDepById(String depId,HttpSession session){
        int value = baseSettingService.removeDepById(depId);
        int value1 = baseSettingService.removeRelDepMenu(depId);
        List<Department> deps = userService.getDepInfo();
        session.setAttribute("deps",deps);
        List<Department> loginDep = userService.getLoginDep();
        session.setAttribute("loginDep",loginDep);
        return null;
    }

    @RequestMapping("/addDep")
    @ResponseBody
    public Map<String,Object> addDep(String depName,String depState, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> depList = baseSettingService.getDepByName(depName);
        System.out.println("depList");
        System.out.println(depList);
        if(depList.size()>0){
            map.put("success",false);
            map.put("msg","该部门已存在");
        }else{
            Map<String,Object> depMap = new HashMap<>();
            String uuid = UUID.randomUUID().toString();
            depMap.put("uuid",uuid);
            depMap.put("depName",depName);
            depMap.put("depState",depState);
            int result = baseSettingService.addDep(depMap);
            System.out.println(result);
            if(result>0){
                map.put("success",true);
                map.put("msg","添加成功");
                List<Department> deps = userService.getDepInfo();
                session.setAttribute("deps",deps);
                List<Department> loginDep = userService.getLoginDep();
                session.setAttribute("loginDep",loginDep);
            }else{
                map.put("success",false);
                map.put("msg","添加失败");
            }
        }
        return map;
    }

    @RequestMapping("/editDep")
    @ResponseBody
    public Map<String,Object> editDep(String depId,String depName,String depState, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> depMap = new HashMap<>();
        depMap.put("depId",depId);
        depMap.put("depName",depName);
        depMap.put("depState",depState);
        int result = baseSettingService.editDep(depMap);
        if(result>0){
            map.put("success",true);
            map.put("msg","修改成功");
            List<Department> deps = userService.getDepInfo();
            session.setAttribute("deps",deps);
            List<Department> loginDep = userService.getLoginDep();
            session.setAttribute("loginDep",loginDep);
        }else{
            map.put("success",false);
            map.put("msg","修改失败");
        }
        return map;
    }
}


