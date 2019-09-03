package edu.zucc.gats.trinity.controller;


import com.google.gson.Gson;
import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static java.lang.System.setErr;

@RestController
@RequestMapping(value = "/rec")
public class RecController {

    private static Gson gson = new Gson();

    @Autowired
    RecommandService recommandService;

    @Autowired
    StudentService studentService;

    @Autowired
    GeneralGradeService generalGradeService;

    @Autowired
    MajorGradeService majorGradeService;

    @Autowired
    SpecialityService specialityService;

    @Autowired
    CollegeService collegeService;


//    @RequestMapping(value = "/all",method = RequestMethod.GET)
//    public RespBean getAllMessage(String studentId){
//        if (studentService.loadStudentById(studentId)==null){
//            return RespBean.error("此学生不存在");
//        }
//        if (recommandService.loadAllRecoByStuId(studentId).size()==0){
//            return RespBean.error("没有任何推荐信息");
//        }
//        List<Recommand> recommandList = recommandService.loadAllMessageFromRec(studentId);
//        for (Recommand recommand:recommandList){
//            out.println(recommand.toString());
//        }
////        return gson.toJson(recommandList);
//        return RespBean.ok("加载推荐信息成功！",recommandList);
//    }

    @RequestMapping(value = "/allString",method = RequestMethod.GET)
    public Object getAllMsgString(String studentId){
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
        return gson.toJson(recommandList);
    }



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBean loadStuRec(String studentId){
        if (studentService.loadStudentById(studentId)==null){
            return RespBean.error("此学生不存在");
        }
        List<GeneralGrade> generalGradeList = generalGradeService.loadAllGeneralGradeById(studentId);
        List<MajorGrade> majorGradeList = majorGradeService.loadAllMajorGradeById(studentId);
        List<Speciality> specialityList = specialityService.loadSpecByStudentId(studentId);
        if (majorGradeList.size() == 0 || generalGradeList.size() == 0){
            return RespBean.error("学生还未添加成绩");
        }
        recoAlgo(studentId,generalGradeList,majorGradeList,specialityList);

        return RespBean.ok("添加新的推荐内容成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Object searchDomainByKey(String key,String studentId){
        List<Recommand> recommandList = recommandService.searchAllMessageFromRecByKey(key,studentId);
        for (Recommand recommand :recommandList){
            out.println(recommand.toString());
        }
        if (recommandList.size() == 0){
            return RespBean.error("未查询到任何信息");
        }
        return gson.toJson(recommandList);
    }

    private boolean recoAlgo(
            String studentId,
            List<GeneralGrade> generalGradeList,
            List<MajorGrade> majorGradeList,
            List<Speciality> specialityList){
        out.println("准备，获得collegeID--------");

        int zuccId = 3;
        int hduId = 5;
        int zjutId = 4;

        out.println("计算相关的条件--------");
        boolean hasD = false;
        int zuccScore = 0;
        int hduScore = 0;
        int countGeneralA = 0;

        for (GeneralGrade generalGrade:generalGradeList){
            out.println("zuccScore: "+zuccScore);
            out.println("hduScore: "+hduScore);
            out.println("countGeneralA: "+countGeneralA);
            if (generalGrade.getGrade().equals("A")){
                countGeneralA += 1;
                if (generalGrade.getSubjectName().equals("语文") ||
                        generalGrade.getSubjectName().equals("数学")||
                        generalGrade.getSubjectName().equals("英语")){
                    zuccScore += 15;
                }
                hduScore +=15;
                zuccScore += 10;
            }else if (generalGrade.getGrade().equals("B")){
                hduScore += 10;
                zuccScore += 8;
            }else if (generalGrade.getGrade().equals("C")){
                hduScore += 4;
                zuccScore += 5;
            }else if (generalGrade.getGrade().equals("D")){
                hasD = true;
            }
        }

        if (!hasD){
            //isZjut
            if (countGeneralA == 5){
                if (specialityList.size()!=0){
                    insertDomainIdByColId(zjutId,studentId,"特长加成,", majorGradeList);
                }
            }else if(countGeneralA > 5) {
                insertDomainIdByColId(zjutId,studentId,"", majorGradeList);
            }
            //hdu
            if (hduScore >= 110){
                insertDomainIdByColId(hduId,studentId,"", majorGradeList);
            }else if(hduScore >= 85){
                if (specialityList.size()!=0){
                    insertDomainIdByColId(hduId,studentId,"特长加成", majorGradeList);
                }
            }
        }
        //zucc
        if (zuccScore >= 60){
            insertDomainIdByColId(zuccId,studentId,"", majorGradeList);
        }else if (zuccScore >= 40){
            if (specialityList.size()!=0){

                insertDomainIdByColId(zuccId,studentId,"特长加成", majorGradeList);
            }
        }
        return true;
    }

    private void insertDomainIdByColId(int colId,
                                       String studentId,
                                       String addReason,
                                       List<MajorGrade> majorGradeList){
//        List<Integer> domainIdList = new ArrayList<>();
        out.println("准备匹配选课  "+colId);
        out.println(addReason);
        List<EnrollReq> enrollReqList = recommandService.loadSubjectReq(colId);
        for (EnrollReq enrollReq:enrollReqList){
            String addReasonPr = addReason;
            if (enrollReq.getSubjectReq().equals("不限")){
                String reason = " 报名条件达标，选考科目不限";
                addReasonPr += reason;
                if(this.addNewReco(new Recommand(studentId,enrollReq.getDomainId(),addReasonPr))){
                    out.println("添加推荐成功");
                }else {
                    out.println("添加推荐失败");
                }

            } else {
                String reason = "学考成绩达标,选考科目";
                addReasonPr += reason;
                for (MajorGrade majorGrade:majorGradeList){
                    if (enrollReq.getSubjectReq().indexOf(majorGrade.getSubjectName()) != -1){
                        addReasonPr = addReasonPr + " " + majorGrade.getSubjectName();
                    }
                }
                addReasonPr += "符合";
                if(this.addNewReco(new Recommand(studentId,enrollReq.getDomainId(),addReasonPr))){
                    out.println("添加推荐成功");
                }else {
                    out.println("添加推荐失败");
                }
            }
            out.println(addReasonPr);
        }
    }

    private boolean addNewReco(Recommand recommand){
        if (recommandService
                .loadRecByStuIdAndDomainId(recommand.getStudentId(),recommand.getDomainId())!=null){
            return false;
        }
        if (recommandService.addNewReco(recommand)==0){
            return false;
        }
        return true;
    }


}
