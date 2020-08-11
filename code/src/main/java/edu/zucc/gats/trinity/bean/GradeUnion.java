package edu.zucc.gats.trinity.bean;

import java.util.List;

public class GradeUnion {
    private List<GeneralGrade> generalGradeList;
    private List<MajorGrade> majorGradeList;

    public GradeUnion(List<GeneralGrade> generalGradeList, List<MajorGrade> majorGradeList) {
        this.generalGradeList = generalGradeList;
        this.majorGradeList = majorGradeList;
    }

    public List<GeneralGrade> getGeneralGradeList() {
        return generalGradeList;
    }

    public void setGeneralGradeList(List<GeneralGrade> generalGradeList) {
        this.generalGradeList = generalGradeList;
    }

    public List<MajorGrade> getMajorGradeList() {
        return majorGradeList;
    }

    public void setMajorGradeList(List<MajorGrade> majorGradeList) {
        this.majorGradeList = majorGradeList;
    }
}
