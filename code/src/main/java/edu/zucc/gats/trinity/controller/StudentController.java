package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import edu.zucc.gats.trinity.service.GeneralGradeService;
import edu.zucc.gats.trinity.service.MajorGradeService;
import edu.zucc.gats.trinity.service.SpecialityService;
import edu.zucc.gats.trinity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    SpecialityService specialityService;
    @Autowired
    GeneralGradeService generalGradeService;
    @Autowired
    MajorGradeService majorGradeService;
}
