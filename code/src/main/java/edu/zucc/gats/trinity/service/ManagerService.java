package edu.zucc.gats.trinity.service;

import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.Role;
import edu.zucc.gats.trinity.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public Manager loadManagerByCode(String manCode){
        return managerMapper.loadManagerByCode(manCode);
    }

    public Manager loadManagerByName(String manName){
        return managerMapper.loadManagerByName(manName);
    }

    public int addManager(Manager manager){
        return managerMapper.addManager(manager);

    }

    public int updateManager(Manager manager){
        return managerMapper.updateManager(manager);
    }

    public int deleteManager(String manCode){
        return managerMapper.deleteManager(manCode);
    }

    public List<Manager> loadAllManagers(){
        return managerMapper.loadAllManagers();
    }

    public Role getManagerRoleByCode(String manCode){
        return managerMapper.getManagerRoleByCode(manCode);
    }


}
