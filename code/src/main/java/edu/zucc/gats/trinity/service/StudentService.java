package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Student;
import edu.zucc.gats.trinity.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    public int updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }

    public int deleteStudent(Student student){
        return studentMapper.deleteStudent(student);
    }

    public Student loadStudentByName(String studentName){
        return studentMapper.loadStudentByName(studentName);
    }

    public Student loadStudentById(String studentId){
        return studentMapper.loadStudentById(studentId);
    }

    public List<Student> searchStudentByKey(String key){
        return studentMapper.searchStudentByKey(key);
    }

    public List<Student> loadAllStudentByPage(int begin,int offset){
        return studentMapper.loadAllStudentByPage(begin,offset);
    }

}
