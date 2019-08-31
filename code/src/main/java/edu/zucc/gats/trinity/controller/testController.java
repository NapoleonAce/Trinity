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


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespBean testModule() throws ParseException {

        out.println("添加报名信息");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginDate = "2019-09-09 00:00:00";
        String finishDate = "2019-09-11 00:00:00";
        for (int i=0;i<3;i++){
            ApplyInfo applyInfo = new ApplyInfo(
                    2,"去索瑟姆群岛搞死龙裔","去索瑟姆群岛搞死龙裔",
                    sdf.parse(beginDate),sdf.parse(finishDate)
            );
            applyInfoService.addNewApplyInfo(applyInfo);
        }

        out.println("查询报名信息");
        List<ApplyInfo> applyInfoList = applyInfoService.loadApplyByColId(2);
        for (ApplyInfo applyInfo:applyInfoList){
            out.println(applyInfo.toString());
        }

        out.println("更改报名信息");
        for (ApplyInfo applyInfo:applyInfoList){
            applyInfo.setApplyCondition("?????");
            applyInfoService.updateApplyInfo(applyInfo);
        }

        out.println("查询报名信息");
        List<ApplyInfo> applyInfoList2 = applyInfoService.loadApplyByColId(2);
        for (ApplyInfo applyInfo:applyInfoList2){
            out.println(applyInfo.toString());
        }

        out.println("删除报名信息");
        for (ApplyInfo applyInfo:applyInfoList2){
            out.println(applyInfoService.deleteApplyInfo(applyInfo));
        }

        return RespBean.ok("测试完成！");
    }

}
