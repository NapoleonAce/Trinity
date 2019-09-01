package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.Domain;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.ApplyInfoService;
import edu.zucc.gats.trinity.service.CollegeService;
import edu.zucc.gats.trinity.service.DomainService;
import edu.zucc.gats.trinity.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/col")
public class CollegeController {

    @Autowired
    ApplyInfoService applyInfoService;
    @Autowired
    DomainService domainService;
    @Autowired
    EnrollService enrollService;
    @Autowired
    CollegeService collegeService;

    //添加专业
    @RequestMapping(value = "/dom",method = RequestMethod.POST)
    public RespBean addNewDom(Domain domain){
        if (domainService.loadDomainById(domain.getDomainId())!=null){
            return RespBean.error("已经存在");
        }
        if (domainService.addDomain(domain)==0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("创建成功");
    }

    //更新专业
    @RequestMapping(value = "/dom",method = RequestMethod.PUT)
    public RespBean updateDom(Domain domain){
        if (domainService.loadDomainById(domain.getDomainId())==null){
            return RespBean.error("不存在此专业");
        }
        if (domainService.updateDomain(domain)==0){
            return RespBean.error("更新失败");
        }
        return RespBean.ok("更新成功");
    }

    //查询专业
    @RequestMapping(value = "/dom",method = RequestMethod.GET)
    public RespBean getAllDomByColId(int colId ){
        List<Domain> domainList = domainService.loadDomainByCol(colId);
        if (domainList.size() == 0){
            return RespBean.error("此学校没有任何专业");
        }
        return RespBean.ok("查询成功！",domainList);
    }
    //删除专业
    @RequestMapping(value = "/dom",method = RequestMethod.DELETE)
    public RespBean deleteDomain(Domain domain){
        if (domainService.loadDomainById(domain.getDomainId())==null){
            return RespBean.error("不存在此专业");
        }
        if (domainService.deleteDomain(domain)==0){
            return RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功");
    }




}
