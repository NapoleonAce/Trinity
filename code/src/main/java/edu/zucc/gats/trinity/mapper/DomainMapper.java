package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Domain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DomainMapper {

    int addDomain(Domain domain);

    int deleteDomain(Domain domain);

    int updateDomain(Domain domain);

    Domain loadDomainByName(String domainName);

    Domain loadDomainById(int domainId);
    
    List<Domain> searchDomainByKey(String key);
    
    List<Domain> loadAllDomainByPage(@Param("begin") int begin,@Param("offset") int offset);

    List<Domain> loadDomainByCol(int collegeId);

}
