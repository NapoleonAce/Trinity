package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.MajorGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MajorGradeMapper {

    int addMajorGrade(MajorGrade majorGrade);

    int updateMajorGrade(MajorGrade majorGrade);

    int deleteMajorGrade(MajorGrade majorGrade);

    MajorGrade loadMajorGrade(@Param("studentId") String studentId,
                              @Param("subjectId") int subjectId);

    List<MajorGrade> loadAllMajorGradeById(String studentId);

}
