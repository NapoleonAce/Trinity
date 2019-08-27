package edu.zucc.gats.trinity.bean;

public class Speciality {
    private String studentId;
    private int speCode;
    private String speType;
    private String speLevel;
    private String content;
    private String evidence;

    public Speciality(String studentId, int speCode, String speType, String speLevel, String content, String evidence) {
        this.studentId = studentId;
        this.speCode = speCode;
        this.speType = speType;
        this.speLevel = speLevel;
        this.content = content;
        this.evidence = evidence;
    }

    public Speciality() {
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "studentId='" + studentId + '\'' +
                ", speCode=" + speCode +
                ", speType='" + speType + '\'' +
                ", speLevel='" + speLevel + '\'' +
                ", content='" + content + '\'' +
                ", evidence='" + evidence + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSpeCode() {
        return speCode;
    }

    public void setSpeCode(int speCode) {
        this.speCode = speCode;
    }

    public String getSpeType() {
        return speType;
    }

    public void setSpeType(String speType) {
        this.speType = speType;
    }

    public String getSpeLevel() {
        return speLevel;
    }

    public void setSpeLevel(String speLevel) {
        this.speLevel = speLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
}
