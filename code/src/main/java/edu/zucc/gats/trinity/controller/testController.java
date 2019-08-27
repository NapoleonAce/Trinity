package edu.zucc.gats.trinity.controller;

import edu.zucc.gats.trinity.bean.College;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.out;

@RestController
public class testController {

    @Autowired
    CollegeService collegeService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public RespBean testModule(){
        College college = new College("弥斯喀托米尔","密歇根","弥斯喀托米尔","克苏鲁发糖！");

        college.setCollegeId(1);
        out.print("test loadName ");
        College college1 = collegeService.loadCollegeByName("克苏鲁");
        out.println(college1.toString());


      out.println("test loadPage");
        List<College> colleges = collegeService.loadAllCollegeByPage(1,2);
        for (College college2 : colleges){
            out.println(college2.toString());
        }

        out.print("test delete ");
        collegeService.deleteCollege(college1);


        return RespBean.ok("测试完成！");
    }

}
