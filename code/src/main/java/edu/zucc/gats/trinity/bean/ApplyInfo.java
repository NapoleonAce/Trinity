package edu.zucc.gats.trinity.bean;


import java.util.Date;

/* * @param dateFormat
  *            传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
  *
  *            ,jdbcType = TIMESTAMP
*
* */
public class ApplyInfo {
    private int collegeId;
    private int applyInfoId;
    private String applyWay;
    private String applyCondition;
    private Date applyBegin;
    private Date applyFinish;

    public ApplyInfo() {
    }

    public ApplyInfo(int collegeId, int applyInfoId, String applyWay, String applyCondition, Date applyBegin, Date applyFinish) {
        this.collegeId = collegeId;
        this.applyInfoId = applyInfoId;
        this.applyWay = applyWay;
        this.applyCondition = applyCondition;
        this.applyBegin = applyBegin;
        this.applyFinish = applyFinish;
    }

    @Override
    public String toString() {
        return "ApplyInfo{" +
                "collegeId=" + collegeId +
                ", applyInfoId=" + applyInfoId +
                ", applyWay='" + applyWay + '\'' +
                ", applyCondition='" + applyCondition + '\'' +
                ", applyBegin=" + applyBegin +
                ", applyFinish=" + applyFinish +
                '}';
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(int applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public String getApplyWay() {
        return applyWay;
    }

    public void setApplyWay(String applyWay) {
        this.applyWay = applyWay;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public Date getApplyBegin() {
        return applyBegin;
    }

    public void setApplyBegin(Date applyBegin) {
        this.applyBegin = applyBegin;
    }

    public Date getApplyFinish() {
        return applyFinish;
    }

    public void setApplyFinish(Date applyFinish) {
        this.applyFinish = applyFinish;
    }
}
