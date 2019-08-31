package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespBean testModule(){
        Domain domain = domainService.loadDomainById(8);

        out.println(domain.toString());

        out.println("添加计划");
        for (int i=0;i<3;i++){
            Enroll enroll = new Enroll(
                    8+i,
                    2019,"物理化学生物",10+i,5000
            );
            out.println(enrollService.addNewEnroll(enroll));
//            enrollService.addNewEnroll(enroll);
        }
        out.println("2019查询计划");
        List<Enroll> enrollList = enrollService.loadEnrollByYear(2019);
        for (Enroll enroll:enrollList){
            out.println(enroll.toString());
        }

        out.println("更新计划");
        for (Enroll enroll:enrollList){
            enroll.setSubjectReq("政治历史地理");
            out.println(enrollService.updateEnroll(enroll));
        }

        out.println("年份和专业再次查询");
        Enroll enroll = enrollService.loadEnrollByDomainIdAndYear(8,2019);
        out.println(enroll.toString());

        out.println("全部查询");
        enrollList = enrollService.loadAllEnroll(0,3);
        for (Enroll enroll1 :enrollList){
            out.println(enroll1.toString());
        }

        out.println("删除专业");
        for (Enroll enroll1:enrollList){
            out.println(enrollService.deleteEnroll(enroll1));
        }


        return RespBean.ok("测试完成！");
    }

}
