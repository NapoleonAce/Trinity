package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

@RestController
public class testController {


    @Autowired
    DomainService domainService;

    @Autowired
    EnrollService enrollService;

    @Autowired
    ApplyInfoService applyInfoService;

    @Autowired
    RecommandService recommandService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespBean testModule() throws ParseException {

        String stuId = "3306";
        Student student = studentService.loadStudentById(stuId);
        out.println(student.toString());

        return RespBean.ok("测试完成！");
    }

}
