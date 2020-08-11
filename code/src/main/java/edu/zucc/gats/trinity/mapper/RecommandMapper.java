package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.EnrollReq;
import edu.zucc.gats.trinity.bean.Recommand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommandMapper {

    int addNewReco(Recommand recommand);

    int updateReco(Recommand recommand);

    int deleteReco(Recommand recommand);

    List<Recommand> loadAllRecoByStuId(String studentId);

    List<Recommand> loadAllMessageFromRec(String studentId);

    List<EnrollReq> loadSubjectReq(int collegeId);

    Recommand loadRecByStuIdAndDomainId(String studentId,int domainId);

    List<Recommand> searchAllMessageFromRecByKey(String key,String studentId);
}
