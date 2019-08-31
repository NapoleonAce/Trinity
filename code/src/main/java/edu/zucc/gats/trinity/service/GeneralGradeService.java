package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.GeneralGrade;
import edu.zucc.gats.trinity.mapper.GeneralGradeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralGradeService {
    @Autowired
    GeneralGradeMapper generalGradeMapper;

    public int addGeneralGrade(GeneralGrade generalGrade){
        return generalGradeMapper.addGeneralGrade(generalGrade);
    }


    public int updateGeneralGrade(GeneralGrade generalGrade){
        return generalGradeMapper.updateGeneralGrade(generalGrade);
    }


    public int deleteGeneralGrade(GeneralGrade generalGrade){
        return generalGradeMapper.deleteGeneralGrade(generalGrade);
    }


    public GeneralGrade loadGeneralGrade( String studentId, int subjectId){
        return generalGradeMapper.loadGeneralGrade(studentId, subjectId);
    }


    public List<GeneralGrade> loadAllGeneralGradeById(String studentId){
        return generalGradeMapper.loadAllGeneralGradeById(studentId);
    }



}
