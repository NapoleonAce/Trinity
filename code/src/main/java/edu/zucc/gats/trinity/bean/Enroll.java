package edu.zucc.gats.trinity.bean;

import java.util.Date;

public class Enroll {
    private int domainId;
    private int year;
    private String subjectReq;
    private int number;
    private float price;

    public Enroll() {
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
                ", year=" + year +
                ", subjectReq='" + subjectReq + '\'' +
                ", number=" + number +
                ", price=" + price +
                '}';
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
