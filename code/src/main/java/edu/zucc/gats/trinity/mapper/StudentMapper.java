package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    
    int addStudent(Student student);
    
    int updateStudent(Student student);
    
    int deleteStudent(Student student);
    
    Student loadStudentByName(String studentName);
    
    Student loadStudentById(String studentId);
    
    List<Student> searchStudentByKey(String key);
    
    List<Student> loadAllStudentByPage(@Param("begin") int begin, @Param("offset") int offset);
    
}
