package edu.zucc.gats.trinity.bean;

public class Domain {
    private int domainId;
    private String domainName;
    private String domainType;
    private int collegeId;
    private String content;

    public Domain() {
    }

    public Domain(String domainName, String domainType, int collegeId, String content) {
        this.domainName = domainName;
        this.domainType = domainType;
        this.collegeId = collegeId;
        this.content = content;
    }

    public Domain(int domainId, String domainName, String domainType, int collegeId, String content) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.domainType = domainType;
        this.collegeId = collegeId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "domainId=" + domainId +
                ", domainName='" + domainName + '\'' +
                ", domainType='" + domainType + '\'' +
                ", collegeId=" + collegeId +
                ", content='" + content + '\'' +
                '}';
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
