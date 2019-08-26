package edu.zucc.gats.trinity.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerRoleMapper {

    int addManagerRole(String manCode,int roleId);

    int deleteManagerRole(String manCode,int roleId);
}
