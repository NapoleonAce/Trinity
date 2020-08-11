package edu.zucc.gats.trinity.bean;

public class EnrollReq {

    int domainId;
    String subjectReq;

    public EnrollReq(int domainId, String subjectReq) {
        this.domainId = domainId;
        this.subjectReq = subjectReq;
    }

    @Override
    public String toString() {
        return "EnrollReq{" +
                "domainId=" + domainId +
                ", subjectReq='" + subjectReq + '\'' +
                '}';
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public String getSubjectReq() {
        return subjectReq;
    }

    public void setSubjectReq(String subjectReq) {
        this.subjectReq = subjectReq;
    }
}
