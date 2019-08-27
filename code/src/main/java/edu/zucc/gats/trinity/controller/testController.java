package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.mapper.ManagerRoleMapper;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import edu.zucc.gats.trinity.service.CollegeService;
import edu.zucc.gats.trinity.service.ManagerRoleService;
import edu.zucc.gats.trinity.service.ManagerService;
import edu.zucc.gats.trinity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.out;

@RestController
public class testController {

    @Autowired
    ManagerRoleService managerRoleService;

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public RespBean testModule(){

        Manager manager = managerService.loadManagerByName("student");
        ManagerRole managerRole = managerRoleService.loadManagerRoleByManager(manager);
        out.println(managerRoleService.updateManagerRole(managerRole));


        return RespBean.ok("测试完成！");
    }

}
