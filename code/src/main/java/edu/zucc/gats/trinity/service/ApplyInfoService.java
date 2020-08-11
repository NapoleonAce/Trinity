package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.ApplyInfo;
import edu.zucc.gats.trinity.mapper.ApplyInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyInfoService {
    @Autowired
    ApplyInfoMapper applyInfoMapper;

    public int addNewApplyInfo(ApplyInfo applyInfo){
        return applyInfoMapper.addNewApplyInfo(applyInfo);
    }

    public int deleteApplyInfo(ApplyInfo applyInfo){
        return applyInfoMapper.deleteApplyInfo(applyInfo);
    }

    public int updateApplyInfo(ApplyInfo applyInfo){
        return applyInfoMapper.updateApplyInfo(applyInfo);
    }

    public ApplyInfo loadApplyByColId(int collegeId){
        return applyInfoMapper.loadApplyByColId(collegeId);
    }


}
