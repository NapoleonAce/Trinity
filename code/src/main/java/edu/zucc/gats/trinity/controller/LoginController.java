package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.mapper.ManagerMapper;
import edu.zucc.gats.trinity.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ManagerService managerService;

    //获取账号密码
    
    //查找账号

    //匹配密码

    //设置token

}
