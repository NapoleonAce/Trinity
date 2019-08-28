package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.MajorGrade;
import edu.zucc.gats.trinity.mapper.MajorGradeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorGradeService {
    @Autowired
    MajorGradeMapper majorGradeMapper;

    public int addMajorGrade(MajorGrade majorGrade){
        return majorGradeMapper.addMajorGrade(majorGrade);
    }

    public int updateMajorGrade(MajorGrade majorGrade){
        return majorGradeMapper.updateMajorGrade(majorGrade);
    }

    public int deleteMajorGrade(MajorGrade majorGrade){
        return majorGradeMapper.deleteMajorGrade(majorGrade);
    }

    public MajorGrade loadMajorGrade( String studentId, String subjectId){
        return majorGradeMapper.loadMajorGrade(studentId, subjectId);
    }

    public List<MajorGrade> loadAllMajorGradeById(String studentId){
        return majorGradeMapper.loadAllMajorGradeById(studentId);
    }
}
