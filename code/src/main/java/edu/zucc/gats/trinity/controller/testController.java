package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.mapper.ManagerRoleMapper;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import edu.zucc.gats.trinity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.out;

@RestController
public class testController {

    @Autowired
    SpecialityService specialityService;
    @Autowired
    GeneralGradeService generalGradeService;
    @Autowired
    MajorGradeService majorGradeService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespBean testModule(){


        return RespBean.ok("测试完成！");
    }

}
