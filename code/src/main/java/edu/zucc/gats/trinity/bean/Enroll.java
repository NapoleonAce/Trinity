package edu.zucc.gats.trinity.bean;

import java.util.Date;

public class Enroll {
    private int domainId;
    private String domainName;
    private String domainType;
    private int year;
    private String subjectReq;
    private int number;
    private float price;

    public Enroll() {
    }

    public Enroll(String domainName, String domainType, int year, String subjectReq, int number, float price) {
        this.domainName = domainName;
        this.domainType = domainType;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
        this.price = price;
    }

    public Enroll(int domainId, String domainName, int year, String subjectReq, int number) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
    }

    public Enroll(int domainId, int year, String subjectReq, int number) {
        this.domainId = domainId;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
    }

    public Enroll(int domainId, String domainName, String domainType, int year, String subjectReq, int number) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.domainType = domainType;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
    }

    public Enroll(int domainId, String domainName, String domainType, int year, String subjectReq, int number, float price) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.domainType = domainType;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
        this.price = price;
    }

    public Enroll(int year, String subjectReq, int number, float price) {
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
        this.price = price;
    }

    public Enroll(int domainId, int year, String subjectReq, int number, float price) {
        this.domainId = domainId;
        this.year = year;
        this.subjectReq = subjectReq;
        this.number = number;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Enroll{" +
                "domainId=" + domainId +
                ", domainName='" + domainName + '\'' +
                ", domainType='" + domainType + '\'' +
                ", year=" + year +
                ", subjectReq='" + subjectReq + '\'' +
                ", number=" + number +
                ", price=" + price +
                '}';
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

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSubjectReq() {
        return subjectReq;
    }

    public void setSubjectReq(String subjectReq) {
        this.subjectReq = subjectReq;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
