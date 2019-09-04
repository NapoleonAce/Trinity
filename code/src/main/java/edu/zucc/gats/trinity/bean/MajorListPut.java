package edu.zucc.gats.trinity.bean;

import java.util.List;

public class MajorListPut {

    private List<MajorGrade> majorGradeList;

    public MajorListPut(List<MajorGrade> majorGradeList) {
        this.majorGradeList = majorGradeList;
    }

    public List<MajorGrade> getMajorGradeList() {
        return majorGradeList;
    }

    public void setMajorGradeList(List<MajorGrade> majorGradeList) {
        this.majorGradeList = majorGradeList;
    }
}
