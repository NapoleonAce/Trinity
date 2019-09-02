package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.ApplyInfo;
import edu.zucc.gats.trinity.bean.Domain;
import edu.zucc.gats.trinity.bean.Enroll;
import edu.zucc.gats.trinity.bean.RespBean;
import edu.zucc.gats.trinity.service.ApplyInfoService;
import edu.zucc.gats.trinity.service.CollegeService;
import edu.zucc.gats.trinity.service.DomainService;
import edu.zucc.gats.trinity.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.out;

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
    public RespBean getAllDomByColId(int collegeId ){
        List<Domain> domainList = domainService.loadDomainByCol(collegeId);
        if (domainList.size() == 0){
            return RespBean.error("此学校没有任何专业");
        }
        return RespBean.ok("加载成功！",domainList);
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

    //计划方面增删该查
    @RequestMapping(value = "/enroll",method = RequestMethod.GET)
    public RespBean getAllEnrollById(int collegeId){
        List<Enroll> enrollList = enrollService.loadAllEnrollByColId(collegeId);
        if (enrollList.size()==0){
            return RespBean.error("该校没有任何招生计划");
        }
        return RespBean.ok("加载成功！",enrollList);
    }

    @RequestMapping(value = "/enroll",method = RequestMethod.POST)
    public RespBean addNewEnroll(int year, int domainId, String subjectReq,float price,int number){
        if (enrollService.loadEnrollByDomainIdAndYear(domainId,year)!=null){
            return RespBean.ok("此计划已经存在");
        }
        Enroll enroll = new Enroll(domainId,year,subjectReq,number,price);
        if (enrollService.addNewEnroll(enroll)==0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功！");
    }

    @RequestMapping(value = "/enroll",method = RequestMethod.PUT)
    public RespBean updateEnroll(Enroll enroll){
        if (enrollService.loadEnrollByDomainIdAndYear(enroll.getDomainId(), enroll.getYear())==null) {
            return RespBean.error("此条计划不存在");
        }
        if (enrollService.updateEnroll(enroll)==0){
            return RespBean.error("更改失败");
        }
        return RespBean.ok("更改成功！");
    }
    @RequestMapping(value = "/enroll",method = RequestMethod.DELETE)
    public RespBean deleteEnroll(Enroll enroll){
        if (enrollService.loadEnrollByDomainIdAndYear(enroll.getDomainId(), enroll.getYear())==null) {
            return RespBean.error("此条计划不存在");
        }
        if (enrollService.deleteEnroll(enroll)==0){
            return RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功");
    }

    //报名信息的增删改查
    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    public RespBean getApplyInfo(int collegeId){
        ApplyInfo applyInfo = applyInfoService.loadApplyByColId(collegeId);
        if (applyInfo == null){
            return RespBean.error("不存在报名信息");
        }
        return RespBean.ok("加载信息成功",applyInfo);
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public RespBean addNewApply(ApplyInfo applyInfo){
        out.println(applyInfo.toString());
        if (applyInfoService.loadApplyByColId(applyInfo.getCollegeId())!=null){
            RespBean.error("院校已经拥有报名信息");
        }
        if (applyInfoService.addNewApplyInfo(applyInfo) == 0){
            RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功");
    }
    @RequestMapping(value = "/apply",method = RequestMethod.PUT)
    public RespBean updateApply(ApplyInfo applyInfo){
        if (applyInfoService.loadApplyByColId(applyInfo.getCollegeId())==null){
            RespBean.error("不存在报名信息");
        }
        if (applyInfoService.updateApplyInfo(applyInfo)==0){
            RespBean.error("更新失败");
        }
        return RespBean.ok("更新成功");
    }
    @RequestMapping(value = "/apply",method = RequestMethod.DELETE)
    public RespBean deleteApply(ApplyInfo applyInfo){
        if (applyInfoService.loadApplyByColId(applyInfo.getCollegeId())==null){
            RespBean.error("不存在报名信息");
        }
        if (applyInfoService.deleteApplyInfo(applyInfo)==0){
            RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功");
    }



}
