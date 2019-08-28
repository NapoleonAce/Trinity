package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Speciality;
import edu.zucc.gats.trinity.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpecialityMapper {

    int addSpeciality(Speciality speciality);

    int updateSpeciality(Speciality speciality);

    int deleteSpeciality(Speciality speciality);

    List<Speciality> loadSpecByStudentId(String studentId);

}
