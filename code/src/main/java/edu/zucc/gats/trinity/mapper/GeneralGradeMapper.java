package edu.zucc.gats.trinity.mapper;


import edu.zucc.gats.trinity.bean.GeneralGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneralGradeMapper {

    int addGeneralGrade(GeneralGrade generalGrade);

    int updateGeneralGrade(GeneralGrade generalGrade);

    int deleteGeneralGrade(GeneralGrade generalGrade);

    GeneralGrade loadGeneralGrade(@Param("studentId") String studentId,
                                  @Param("subjectId") String subjectId);

    List<GeneralGrade> loadAllGeneralGradeById(String studentId);
}
