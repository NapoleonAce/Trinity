package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.ManagerRole;
import edu.zucc.gats.trinity.mapper.ManagerRoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerRoleService {

    @Autowired
    ManagerRoleMapper managerRoleMapper;

    public int addManagerRole(String manCode,int roleId){
        return managerRoleMapper.addManagerRole(manCode, roleId);
    }

    public int deleteManagerRole(String manCode){
        return managerRoleMapper.deleteManagerRole(manCode);
    }

    public int updateManagerRole(ManagerRole managerRole){
        return managerRoleMapper.updateManagerRole(managerRole);
    }

    public ManagerRole loadManagerRoleByManager(Manager manager){
        return managerRoleMapper.loadManagerRoleByManager(manager);
    }

}
