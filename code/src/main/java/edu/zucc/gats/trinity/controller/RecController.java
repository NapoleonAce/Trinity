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


        int zuccId = 0;
        int hduId = 0;
        int zjutId = 0;

        boolean hasD = false;
        int zuccScore = 0;
        int hduScore = 0;
        int countGeneralA = 0;

        for (GeneralGrade generalGrade:generalGradeList){
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

        return RespBean.ok("添加新的推荐内容成功");
    }

    private void insertDomainIdByColId(int colId,
                                       String studentId,
                                       String addReason,
                                       List<MajorGrade> majorGradeList){
//        List<Integer> domainIdList = new ArrayList<>();
        List<EnrollReq> enrollReqList = recommandService.loadSubjectReq(colId);
        for (EnrollReq enrollReq:enrollReqList){
            if (enrollReq.getSubjectReq().equals("不限")){
                String reason = " 报名条件达标，选考科目不限";
                addReason += reason;
                recommandService.addNewReco(new Recommand(studentId,enrollReq.getDomainId(),addReason));

            } else {
                for (MajorGrade majorGrade:majorGradeList){
                    String reason = "学考成绩达标,选考科目";
                    addReason += reason;
                    if (enrollReq.getSubjectReq().indexOf(majorGrade.getSubjectName()) != -1){
                        addReason = addReason + " " + majorGrade.getSubjectName();
                    }
                }
                addReason += "符合";
            }
        }
    }

  
}
