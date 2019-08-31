package edu.zucc.gats.trinity.service;

import com.sun.org.apache.regexp.internal.RE;
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

    public String getSubjectName(int subjectId){
        return studentMapper.getSubjectName(subjectId);
    }


    public int getSubjectId(String subjectName){
        return studentMapper.getSubjectId(subjectName);
    }

    public int deleteStuById(String studentId){
        return studentMapper.deleteStuById(studentId);
    }
}
