package edu.zucc.gats.trinity.controller;


import com.google.gson.Gson;
import edu.zucc.gats.trinity.bean.*;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import edu.zucc.gats.trinity.service.GeneralGradeService;
import edu.zucc.gats.trinity.service.MajorGradeService;
import edu.zucc.gats.trinity.service.SpecialityService;
import edu.zucc.gats.trinity.service.StudentService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@RestController
@RequestMapping(value = "/stuMan")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    SpecialityService specialityService;
    @Autowired
    GeneralGradeService generalGradeService;
    @Autowired
    MajorGradeService majorGradeService;

    @RequestMapping(value = "/select/stu",method = RequestMethod.GET)
    public RespBean getStudentData(){
        List<Student>studentList = studentService.loadAllStudentByPage(0,20);
        if (studentList.get(0)==null){
            return RespBean.error("没有任何用户");
        }
        return RespBean.ok("加载用户成功！",studentList);
    }

    @RequestMapping(value = "/select/grade",method = RequestMethod.GET)
    public RespBean getStudentGrade(@RequestParam("studentId") String studentId){
        //返回所有成绩
//        out.println(studentId);
        List<GeneralGrade> generalGradeList = generalGradeService.loadAllGeneralGradeById(studentId);
        List<MajorGrade> majorGradeList = majorGradeService.loadAllMajorGradeById(studentId);
        if (generalGradeList.size() == 0){
            return RespBean.error("没有任何学考成绩");
        }
        if (majorGradeList.size() == 0){
            return RespBean.error("没有任何选考成绩");
        }
        GradeUnion gradeUnion = new GradeUnion(generalGradeList,majorGradeList);
        return RespBean.ok("加载成绩成功！",gradeUnion);
    }

    @RequestMapping(value = "/select/gen",method = RequestMethod.GET)
    public RespBean getStudentGeneralGrade(String studentId){
        List<GeneralGrade> generalGradeList = generalGradeService.loadAllGeneralGradeById(studentId);
        if (generalGradeList.size() == 0){
            return RespBean.error("没有任何学考成绩");
        }
        return RespBean.ok("加载学考成绩成功！",generalGradeList);
    }

    @RequestMapping(value = "/select/major",method = RequestMethod.GET)
    public RespBean getStudentMajorGrade(String studentId){
        List<MajorGrade> majorGradeList = majorGradeService.loadAllMajorGradeById(studentId);
        if (majorGradeList.size() == 0){
            return RespBean.error("没有任何选考成绩");
        }
        return RespBean.ok("加载选考成绩成功！",majorGradeList);
    }

    @RequestMapping(value = "/select/spe",method = RequestMethod.GET)
    public RespBean getStudentSpe(String studentId){

        List<Speciality> specialityList = specialityService.loadSpecByStudentId(studentId);
//        out.println(specialityList.size());
        if (specialityList.size() == 0){
            return RespBean.error("没有任何特长");
        }
        return RespBean.ok("加载特长成功！",specialityList);
    }



    @RequestMapping(value = "/add/stu",method = RequestMethod.POST)
    public RespBean addNewStu(Student student){

        if (studentService.loadStudentById(student.getStudentId())!=null){
            return RespBean.error("ID 重复");
        }
        if (studentService.addStudent(student)==0){
            return RespBean.error("添加失败");
        }

        return RespBean.ok("添加成功！");
    }
    @RequestMapping(value = "/add/grade",method = RequestMethod.POST)
    public RespBean addNewGrade(@RequestParam("gradeUnion") GradeUnion gradeUnion){
        for (GeneralGrade generalGrade:gradeUnion.getGeneralGradeList()){
            if (generalGradeService.loadGeneralGrade(generalGrade.getStudentId(),generalGrade.getSubjectId())!=null){
                RespBean.error("学考存在重复内容");
        }
            if (generalGradeService.addGeneralGrade(generalGrade)==0){
                RespBean.error("学考成绩添加失败");
            }
        }
        for (MajorGrade majorGrade:gradeUnion.getMajorGradeList()){
            if (majorGradeService.loadMajorGrade(majorGrade.getStudentId(),majorGrade.getSubjectId())!=null){
                RespBean.error("选考存在重复内容");
            }
            if (majorGradeService.addMajorGrade(majorGrade)==0){
                RespBean.error("选考成绩添加失败");
            }
        }
        return RespBean.ok("添加成绩成功！");
    }

    @RequestMapping(value = "/add/spe",method = RequestMethod.POST)
    public RespBean addNewSpe(@RequestParam("speType")String speType,
                              @RequestParam("speLevel")String speLevel,
                              @RequestParam("content")String content,
                              @RequestParam("evidence")String evidence,
                              @RequestParam("studentId")String studentId){
        Speciality speciality = new Speciality(studentId,speType,speLevel,content,evidence);
        if (specialityService.addSpeciality(speciality)!=0){
            RespBean.ok("添加特长失败");
        }

        return RespBean.ok("添加特长成功！");
    }


    @RequestMapping(value = "/update/stu",method = RequestMethod.PUT)
    public RespBean updateStu(@RequestParam("oldStudentId")String oldStudentId,
                              Student student){
        if (!oldStudentId.equals(student.getStudentId())){
            if (studentService.loadStudentById(student.getStudentId())!=null){
                return RespBean.error("ID 重复");
            }
        }
        if (studentService.updateStudent(student)==0){
            return RespBean.error("更新学生信息失败");
        }

        return RespBean.ok("更新学生信息成功");
    }

    @RequestMapping(value = "/update/grade",method = RequestMethod.PUT)
    public RespBean updateGrade(@RequestBody GradeUnion gradeUnion){
        //直传，记得加入studentId

        for (GeneralGrade generalGrade:gradeUnion.getGeneralGradeList()){
            if (generalGradeService.updateGeneralGrade(generalGrade)==0){
               return RespBean.error("更新学考成绩失败");
            }
        }
        for (MajorGrade majorGrade:gradeUnion.getMajorGradeList()){
            if (majorGradeService.updateMajorGrade(majorGrade)==0){
                return RespBean.error("更新选考成绩失败");
            }
        }

        return RespBean.ok("更新成绩成功！");
    }
    private static Gson gson = new Gson();
    @RequestMapping(value = "/update/gen",method = RequestMethod.POST)
    public RespBean updateGeneralGrade(@RequestBody String list){

        out.println(list);
        GeneralListPut generalListPut = gson.fromJson(list,GeneralListPut.class);

        for (GeneralGrade generalGrade:generalListPut.getGeneralGradeList()){
            if (generalGradeService.updateGeneralGrade(generalGrade)==0){
                return RespBean.error("更新学考成绩失败");
            }
        }
        return RespBean.ok("更新学考成绩成功！");
    }

    @RequestMapping(value = "/update/major",method = RequestMethod.POST)
    public RespBean updateMajorGrade(@RequestBody String list){

        out.println(list);

        MajorListPut majorListPut = gson.fromJson(list,MajorListPut.class);

        for (MajorGrade majorGrade:majorListPut.getMajorGradeList()){
            if (majorGradeService.updateMajorGrade(majorGrade)==0){
                return RespBean.error("更新选考成绩失败");
            }
        }
        return RespBean.ok("更新选考成绩成功！");
    }
    @RequestMapping(value = "/update/spe",method = RequestMethod.PUT)
    public RespBean updateSpe(Speciality speciality){
//        Speciality oldSpe = specialityService.loadSpeBySpeCode(speciality.getSpeCode());
//        if (oldSpe == null){
//            return RespBean.error("特长不存在");
//        }
//        if (!oldSpe.getStudentId().equals(speciality.getStudentId())){
//            return RespBean.error("该学生没有此特长");
//        }
        if (specialityService.updateSpeciality(speciality)==0){
            return RespBean.error("特长修改失败");
        }
        return RespBean.ok("更新特长成功！");
    }

    @RequestMapping(value = "/delete/stu",method = RequestMethod.DELETE)
    public RespBean deleteStu(String studentId){
        if (studentService.loadStudentById(studentId)==null){
            return RespBean.error("不存在此学生");
        }
        if (studentService.deleteStuById(studentId)==0){
            return RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功");
    }

    @RequestMapping(value = "/delete/spe",method = RequestMethod.DELETE)
    public RespBean deleteSpe(Speciality speciality){
        if (specialityService.loadSpeBySpeCode(speciality.getSpeCode())==null){
            return RespBean.error("不存在此特长");
        }
        if (specialityService.deleteSpeciality(speciality)==0){
            return RespBean.error("删除失败");
        }

        return RespBean.ok("删除成功");
    }


}
