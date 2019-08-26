package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollegeMapper {

    int addCollege(College college);

    int deleteCollege(College college);

    int updateCollege(College college);

    College loadCollegeByName(String colName);

    College loadCollegeById(String colId);

    //根据关键字模糊查找大学
    List<College> searchCollegeByKey(String key);

    //分页查询大学
    List<College> loadAllCollegeByPage(@Param("begin") int begin,@Param("offset") int offset);
}
