package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Domain;
import edu.zucc.gats.trinity.mapper.DomainMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {
    @Autowired
    DomainMapper domainMapper;

    public int addDomain(Domain domain){
        return domainMapper.addDomain(domain);
    }

    public int deleteDomain(Domain domain){
        return domainMapper.deleteDomain(domain);
    }

    public int updateDomain(Domain domain){
        return domainMapper.updateDomain(domain);
    }

    public Domain loadDomainByName(String domainName){
        return domainMapper.loadDomainByName(domainName);
    }

    public Domain loadDomainById(int domainId){
        return domainMapper.loadDomainById(domainId);
    }

    public List<Domain> loadDomainByCol(int collegeId){
        return domainMapper.loadDomainByCol(collegeId);
    }

    public List<Domain> searchDomainByKey(String key){
        return domainMapper.searchDomainByKey(key);
    }

    public List<Domain> loadAllDomainByPage(int begin,int offset){
        return domainMapper.loadAllDomainByPage(begin, offset);
    }

}