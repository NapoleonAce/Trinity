package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.College;
import edu.zucc.gats.trinity.mapper.CollegeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    CollegeMapper collegeMapper;

    public int addCollege(College college){
        return collegeMapper.addCollege(college);
    }

    public int deleteCollege(College college){
        return collegeMapper.deleteCollege(college);
    }

    public int updateCollege(College college){
        return collegeMapper.updateCollege(college);
    }

    public College loadCollegeByName(String colName){
        return collegeMapper.loadCollegeByName(colName);
    }

    public College loadCollegeById(int colId){
        return collegeMapper.loadCollegeById(colId);
    }
  //根据关键字模糊查找大学
    public List<College> searchCollegeByKey(String key){
        return collegeMapper.searchCollegeByKey(key);
    }

   //分页查询大学
    public List<College> loadAllCollegeByPage(@Param("begin") int begin, @Param("offset") int offset){
        return collegeMapper.loadAllCollegeByPage(begin,offset);
    }


}
