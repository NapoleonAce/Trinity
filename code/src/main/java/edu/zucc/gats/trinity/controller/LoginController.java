package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.mapper.ManagerMapper;
import edu.zucc.gats.trinity.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@RestController
public class LoginController {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespBean managerLogin(
            @RequestParam("manName")String manName,
            @RequestParam("password")String password
            ){

        if (manName.isEmpty() || password.isEmpty()){
            return RespBean.error("账号或者密码为空");
        }
        Manager manager = managerService.loadManagerByName(manName);
        if (manager==null){
            return RespBean.error("没有此用户");
        }
        if (!(manager.getPassword().equals(password))){
            return RespBean.error("密码错误");
        }
        //对权限进行匹配，返回角色所有信息
        out.println(manager.toString());
        return RespBean.ok("登录成功!",manager);
    }

}
