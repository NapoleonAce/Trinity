package edu.zucc.gats.trinity.bean;


public class Recommand {
    private String studentId;
    private int domainId;
    private String reason;

    public Recommand(){

    }
    public Recommand(String studentId, int domainId, String reason) {
        this.studentId = studentId;
        this.domainId = domainId;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Recommand{" +
                "studentId='" + studentId + '\'' +
                ", domainId=" + domainId +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
