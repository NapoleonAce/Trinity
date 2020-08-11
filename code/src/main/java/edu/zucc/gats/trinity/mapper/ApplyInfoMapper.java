package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.ApplyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyInfoMapper {
    
    int addNewApplyInfo(ApplyInfo applyInfo);
    
    int deleteApplyInfo(ApplyInfo applyInfo);
    
    int updateApplyInfo(ApplyInfo applyInfo);
    
    ApplyInfo loadApplyByColId(int collegeId);
    
}
