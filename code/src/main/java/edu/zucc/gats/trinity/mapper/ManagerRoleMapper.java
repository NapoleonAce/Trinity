package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.ManagerRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerRoleMapper {

    int addManagerRole(@Param("manCode")String manCode, @Param("roleId") int roleId);

    int deleteManagerRole(@Param("manCode")String manCode);

    int updateManagerRole(ManagerRole managerRole);

    ManagerRole loadManagerRoleByManager(Manager manager);

}
