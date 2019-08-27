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
            @RequestParam("password")String password,
            @RequestParam("role")String role){

        out.println("i am in managerLogin");
        //防止账号密码为空
        if (manName.isEmpty() || password.isEmpty()){
            return RespBean.error("账号或者密码为空");
        }
        out.println("avoid name or password is null");
        //查找账号
        Manager manager = managerService.loadManagerByName(manName);
        if (manager==null){
            return RespBean.error("没有此用户");
        }
        out.println("equals pwd");
        if (!(manager.getPassword().equals(password))){
            return RespBean.error("密码错误");
        }
        //设置token
        return RespBean.ok("登录成功!");
    }

}
