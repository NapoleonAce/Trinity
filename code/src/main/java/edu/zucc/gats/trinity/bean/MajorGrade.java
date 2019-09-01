package edu.zucc.gats.trinity.bean;

public class MajorGrade {
    private String studentId;
    private String subjectName;
    private int subjectId;
    private int score;

    public MajorGrade(String studentId, String subjectName, int subjectId, int score) {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.score = score;
    }
//
//    public MajorGrade(String studentId, int subjectId, int score) {
//        this.studentId = studentId;
//        this.subjectId = subjectId;
//        this.score = score;
//    }

    public MajorGrade() {
    }


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "MajorGrade{" +
                "studentId='" + studentId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectId=" + subjectId +
                ", score=" + score +
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
