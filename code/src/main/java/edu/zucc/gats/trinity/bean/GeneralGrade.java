package edu.zucc.gats.trinity.bean;

public class GeneralGrade {
    private String studentId;
    private int subjectId;
    private String grade;

    public GeneralGrade(String studentId, int subjectId, String grade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    public GeneralGrade() {
    }

    @Override
    public String toString() {
        return "GeneralGrade{" +
                "studentId='" + studentId + '\'' +
                ", subjectId=" + subjectId +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
