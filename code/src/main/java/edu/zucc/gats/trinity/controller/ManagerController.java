package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.Manager;
import edu.zucc.gats.trinity.bean.ManagerRole;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.ManagerRoleService;
import edu.zucc.gats.trinity.service.ManagerService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    ManagerRoleService managerRoleService;

    //添加新用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBean addNewManager(@RequestParam("manName")String manName,
                                  @RequestParam("password")String password,
                                  @RequestParam("manCode")String manCode,
                                  @RequestParam("roleId")int roleId,
                                  HttpServletResponse response){

        Manager manager = new Manager(manCode,manName,password);
        if (managerService.loadManagerByCode(manager.getCode())!=null){
            return RespBean.error("用户代码已经存在");
        }

        if (managerService.loadManagerByName(manager.getName())!=null){
            return RespBean.error("用户名已经存在");
        }

        if (managerRoleService.loadManagerRoleByManager(manager)!=null){
            return RespBean.error("用户角色权限已经存在");
        }

        if (managerService.addManager(manager)==0){
            return RespBean.error("添加失败");
        }

        if (managerRoleService.addManagerRole(manager.getCode(),roleId) == 0){
            return RespBean.error("创建角色权限失败");
        }
        //如何回传权限……
        List<Object> obj = new ArrayList<>();
        obj.add(manager);
        obj.add(roleId);
        return RespBean.ok("添加新用户成功！",obj);
    }

    //更新用户
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public RespBean updateManager(@RequestParam("newManName")String newManName,
                                  @RequestParam("oldManName")String oldManName,
                                  @RequestParam("oldPassword")String oldPassword,
                                  @RequestParam("newPassword")String newPassword,
                                  @RequestParam("manCode")String manCode,
                                  @RequestParam("roleId")int roleId,
                                  HttpServletResponse response){

        //校验密码，信息修改，角色修改
        Manager manager = managerService.loadManagerByName(oldManName);
        if (manager == null){
            return RespBean.error("用户不存在");
        }
        if (managerService.loadManagerByName(newManName) != null){
            return RespBean.error("用户名已经被注册");
        }
        if (!manager.getPassword().equals(oldPassword)){
            return RespBean.error("密码错误");
        }
        Manager newManager = new Manager(manCode,newManName,newPassword);
//        newManager.setCode(manager.getCode());

        if (managerService.updateManager(newManager) == 0){
            out.println(newManager.toString());
            return RespBean.error("修改失败");
        }
        ManagerRole managerRole = managerRoleService.loadManagerRoleByManager(manager);
        managerRole.setRoleId(roleId);
        if (managerRoleService.updateManagerRole(managerRole) == 0){
            return RespBean.error("修改角色权限失败");
        }
        //试试看
        List<Object> obj = new ArrayList<>();
        obj.add(newManager);
        obj.add(roleId);
        return RespBean.ok("修改成功！",obj);
    }

    //删除用户
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public RespBean deleteManager(@RequestParam("manName")String manName,
                                  HttpServletResponse response){
        //是否存在，删除权限和删除角色
        Manager manager = managerService.loadManagerByName(manName);
        if (manager == null){
            return RespBean.error("用户不存在");
        }
        if (managerRoleService.deleteManagerRole(manager.getCode()) == 0 ){
            return RespBean.error("删除角色权限失败");
        }
        if (managerService.deleteManager(manager.getCode()) == 0){
            return RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功！");
    }

    //查询
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public RespBean getAllManager(HttpServletResponse response){
        //可以返回一个List of map的形式
        List<Manager> managerList = managerService.loadAllManagers();
        if (managerList.get(0)==null){
            return RespBean.error("没有任何用户");
        }
        for (int i=0;i<managerList.size();i++){
            Manager manager = managerList.get(i);
            ManagerRole managerRole = managerRoleService.loadManagerRoleByManager(managerList.get(i));
            manager.setRoleId(managerRole.getRoleId());
            managerList.set(i,manager);
        }
        return RespBean.ok("已经查找到",managerList);

    }
}
