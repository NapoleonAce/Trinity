package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.mapper.ManagerRoleMapper;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import edu.zucc.gats.trinity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @Autowired
    StudentService studentService;


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespBean testModule(){

        Student student = studentService.loadStudentByName("查理曼");
        out.println(student.toString());


        out.println("添加特长");
        for (int i=8;i<=10;i++){
            Speciality speciality = new Speciality(
                    student.getStudentId(),"魔法","高级",
                    "很厉害","得到了魔法学院的认可");
            out.println(specialityService.addSpeciality(speciality));
        }
        //查询成绩
        out.println("查询特长");
        List<Speciality> specialityList = specialityService.loadSpecByStudentId(student.getStudentId());
        for (Speciality speciality:specialityList){
            out.println(speciality.toString());
        }

        out.println("更改特长");
        for (int i=4;i<=6;i++){
            Speciality speciality = new Speciality(
                    student.getStudentId(),i,"体术","高级",
                    "很厉害","得到了战友团先驱的认可");
            out.println(specialityService.updateSpeciality(speciality));
        }
        out.println("再次查询");
        specialityList = specialityService.loadSpecByStudentId(student.getStudentId());
        for (Speciality speciality:specialityList){
            out.println(speciality.toString());
        }

        out.println("删除特长");
        for (Speciality speciality:specialityList){
            out.println(specialityService.deleteSpeciality(speciality));
        }

        return RespBean.ok("测试完成！");
    }

}
