package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Enroll;
import jdk.nashorn.internal.ir.EmptyNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnrollMapper {

    int addNewEnroll(Enroll enroll);

    int updateEnroll(Enroll enroll);

    int deleteEnroll(Enroll enroll);

    Enroll loadEnrollByDomainIdAndYear(
            @Param("domainId")int domainId, @Param("year") int year);

    List<Enroll> loadEnrollByYear(int year);

    List<Enroll> loadAllEnroll(
            @Param("begin")int begin,@Param("offset")int offset);

    List<Enroll> loadAllEnrollByColId(int collegeId);
}
