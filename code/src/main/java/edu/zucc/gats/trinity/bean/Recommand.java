package edu.zucc.gats.trinity.bean;


public class Recommand {
    private String studentId;
    private int domainId;
    private String reason;

    private String domainName;
    private String domainType;
    private String domainContent;
    private int number;
    private float price;
    private String subjectReq;
    private College college;


    public Recommand(String studentId, int domainId, String reason, String domainName, String domainType, String domainContent, int number, float price, String subjectReq, College college) {
        this.studentId = studentId;
        this.domainId = domainId;
        this.reason = reason;
        this.domainName = domainName;
        this.domainType = domainType;
        this.domainContent = domainContent;
        this.number = number;
        this.price = price;
        this.subjectReq = subjectReq;
        this.college = college;
    }

    public Recommand(){

    }
    public Recommand(String studentId, int domainId, String reason) {
        this.studentId = studentId;
        this.domainId = domainId;
        this.reason = reason;
    }


    public Recommand(int domainId, String reason, String domainName, String domainType, String domainContent, int number, float price, String subjectReq, College college) {
        this.domainId = domainId;
        this.reason = reason;
        this.domainName = domainName;
        this.domainType = domainType;
        this.domainContent = domainContent;
        this.number = number;
        this.price = price;
        this.subjectReq = subjectReq;
        this.college = college;
    }

    public Recommand(String studentId, String reason, String domainName, String domainType, String domainContent, int number, float price, String subjectReq, College college) {
        this.studentId = studentId;
        this.reason = reason;
        this.domainName = domainName;
        this.domainType = domainType;
        this.domainContent = domainContent;
        this.number = number;
        this.price = price;
        this.subjectReq = subjectReq;
        this.college = college;
    }

    public Recommand(String reason, String domainName, String domainType, String domainContent, int number, float price, String subjectReq, College college) {
        this.reason = reason;
        this.domainName = domainName;
        this.domainType = domainType;
        this.domainContent = domainContent;
        this.number = number;
        this.price = price;
        this.subjectReq = subjectReq;
        this.college = college;
    }

    @Override
    public String toString() {
        return "Recommand{" +
                "studentId='" + studentId + '\'' +
                ", domainId=" + domainId +
                ", reason='" + reason + '\'' +
                ", domainName='" + domainName + '\'' +
                ", domainType='" + domainType + '\'' +
                ", domainContent='" + domainContent + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", subjectReq='" + subjectReq + '\'' +
                ", college=" + college +
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
