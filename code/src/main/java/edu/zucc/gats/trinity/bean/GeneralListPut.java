package edu.zucc.gats.trinity.bean;

import java.util.List;

public class GeneralListPut {
    private List<GeneralGrade> generalGradeList;

    public GeneralListPut(List<GeneralGrade> generalGradeList) {
        this.generalGradeList = generalGradeList;
    }

    public GeneralListPut() {
    }

    public List<GeneralGrade> getGeneralGradeList() {
        return generalGradeList;
    }

    public void setGeneralGradeList(List<GeneralGrade> generalGradeList) {
        this.generalGradeList = generalGradeList;
    }
}
