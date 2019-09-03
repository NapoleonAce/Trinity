package edu.zucc.gats.trinity.bean;

public class College {
    private int collegeId;
    private String collegeName;
    private String province;
    private String city;
    private String content;

    private ApplyInfo applyInfo;

    public College() {
    }

    public College(String collegeName, String province, String city, String content, ApplyInfo applyInfo) {
        this.collegeName = collegeName;
        this.province = province;
        this.city = city;
        this.content = content;
        this.applyInfo = applyInfo;
    }

    public College(String collegeName, String province, String city, String content) {
        this.collegeName = collegeName;
        this.province = province;
        this.city = city;
        this.content = content;
    }

    public College(int collegeId, String collegeName, String province, String city, String content, ApplyInfo applyInfo) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.province = province;
        this.city = city;
        this.content = content;
        this.applyInfo = applyInfo;
    }

    public ApplyInfo getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(ApplyInfo applyInfo) {
        this.applyInfo = applyInfo;
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
