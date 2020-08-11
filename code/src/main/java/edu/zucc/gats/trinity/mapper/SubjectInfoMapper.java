package edu.zucc.gats.trinity.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectInfoMapper {

    String getSubjectName(int subjectId);
}
