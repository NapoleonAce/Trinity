package edu.zucc.gats.trinity.controller;


import com.google.gson.Gson;
import edu.zucc.gats.trinity.bean.Recommand;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.RecommandService;
import edu.zucc.gats.trinity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.out;

@RestController
@RequestMapping(value = "/rec")
public class RecController {

    private static Gson gson = new Gson();
    @Autowired
    RecommandService recommandService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public RespBean getAllMessage(String studentId){
        if (studentService.loadStudentById(studentId)==null){
            return RespBean.error("此学生不存在");
        }
        if (recommandService.loadAllRecoByStuId(studentId).size()==0){
            return RespBean.error("没有任何推荐信息");
        }
        List<Recommand> recommandList = recommandService.loadAllMessageFromRec(studentId);
        for (Recommand recommand:recommandList){
            out.println(recommand.toString());
        }
//        return gson.toJson(recommandList);
        return RespBean.ok("加载推荐信息成功！",recommandList);
    }

    @RequestMapping(value = "/allString",method = RequestMethod.GET)
    public String getAllMsgString(String studentId){
        List<Recommand> recommandList = recommandService.loadAllMessageFromRec(studentId);
        for (Recommand recommand:recommandList){
            out.println(recommand.toString());
        }
        return gson.toJson(recommandList);
    }
}
