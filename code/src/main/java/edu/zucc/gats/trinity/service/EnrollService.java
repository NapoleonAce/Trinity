package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Enroll;
import edu.zucc.gats.trinity.mapper.EnrollMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollService {
    @Autowired
    EnrollMapper enrollMapper;

    public int addNewEnroll(Enroll enroll){
        return enrollMapper.addNewEnroll(enroll);
    }

    public int updateEnroll(Enroll enroll){
        return enrollMapper.updateEnroll(enroll);
    }

    public int deleteEnroll(Enroll enroll){
        return enrollMapper.deleteEnroll(enroll);
    }

    public Enroll loadEnrollByDomainIdAndYear(int domainId, int year){
        return enrollMapper.loadEnrollByDomainIdAndYear(domainId, year);
    }

    public List<Enroll> loadEnrollByYear(int year){
        return enrollMapper.loadEnrollByYear(year);
    }

    public List<Enroll> loadAllEnroll(int begin,int offset){
        return enrollMapper.loadAllEnroll(begin, offset);
    }

    public List<Enroll> loadAllEnrollByColId(int collegeId){
        return enrollMapper.loadAllEnrollByColId(collegeId);
    }
}
