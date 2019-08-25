package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ManagerMapper {
    //
    Manager loadManagerByCode(String manCode);

    Manager loadManagerByName(String manName);

    Role getManagerRoleByCode(String manCode);

    int addManager(Manager manager);

    int updateManager(Manager manager);

    int deleteManager(String manCode);

    List<Manager> loadAllManagers();

}
