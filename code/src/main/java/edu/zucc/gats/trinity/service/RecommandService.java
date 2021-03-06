package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.EnrollReq;
import edu.zucc.gats.trinity.bean.Recommand;
import edu.zucc.gats.trinity.mapper.RecommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommandService {
    @Autowired
    RecommandMapper recommandMapper;

    public int addNewReco(Recommand recommand){
        return recommandMapper.addNewReco(recommand);
    }

    public int updateReco(Recommand recommand){
        return recommandMapper.updateReco(recommand);
    }

    public int deleteReco(Recommand recommand){
        return recommandMapper.deleteReco(recommand);
    }

    public List<Recommand> loadAllRecoByStuId(String studentId){
        return recommandMapper.loadAllRecoByStuId(studentId);
    }

    public List<Recommand> loadAllMessageFromRec(String studentId){
        return recommandMapper.loadAllMessageFromRec(studentId);
    }

    public List<EnrollReq> loadSubjectReq(int collegeId){
        return recommandMapper.loadSubjectReq(collegeId);
    }
    public Recommand loadRecByStuIdAndDomainId(String studentID,int domainId){
        return recommandMapper.loadRecByStuIdAndDomainId(studentID,domainId);
    }


    public List<Recommand> searchAllMessageFromRecByKey(String key,String studentId){
        return recommandMapper.searchAllMessageFromRecByKey(key,studentId);
    }
}
