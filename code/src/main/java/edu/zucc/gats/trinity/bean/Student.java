package edu.zucc.gats.trinity.bean;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private String examId;
    private String gender;
    private String phone;
    private String school;
    private String province;
    private List<GeneralGrade> generalGradeList;
    private List<MajorGrade> majorGradeList;
    private List<Speciality> specialityList;

    public Student(String studentId, String studentName,
                   String examId, String gender,
                   String phone, String school, String province) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.examId = examId;
        this.gender = gender;
        this.phone = phone;
        this.school = school;
        this.province = province;

    }

    public Student(String studentId, String studentName,
                   String examId, String gender,
                   String phone, String school,
                   String province, List<GeneralGrade> generalGradeList,
                   List<MajorGrade> majorGradeList,
                   List<Speciality> specialityList) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.examId = examId;
        this.gender = gender;
        this.phone = phone;
        this.school = school;
        this.province = province;
        this.generalGradeList = generalGradeList;
        this.majorGradeList = majorGradeList;
        this.specialityList = specialityList;

    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", examId='" + examId + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", province='" + province + '\'' +
                ", generalGradeList=" + generalGradeList +
                ", majorGradeList=" + majorGradeList +
                ", specialityList=" + specialityList +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
