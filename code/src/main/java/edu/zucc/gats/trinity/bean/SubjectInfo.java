package edu.zucc.gats.trinity.bean;

public class SubjectInfo {
    private int subjectId;
    private String subjectName;

    public SubjectInfo(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public SubjectInfo(String subjectName) {
        this.subjectName = subjectName;
    }

    public SubjectInfo() {
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
