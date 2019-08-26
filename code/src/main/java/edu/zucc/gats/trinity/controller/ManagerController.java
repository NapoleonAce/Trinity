package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    //添加新用户
    @RequestMapping(value = "/addNew",method = RequestMethod.POST)
    public RespBean addNewManager(@RequestParam("manName")String manName,
                                  @RequestParam("password")String password,
                                  @RequestParam("manCode")String manCode,
                                  @RequestParam("roleId")String roleId,
                                  HttpServletResponse response){
        if (managerService.addManager(new Manager(manCode,manName,password))==0){
            return RespBean.error("添加失败");
        }
        //添加角色
        return RespBean.ok("添加新用户成功！");
    }

    //

}
