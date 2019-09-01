package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.bean.Domain;
import edu.zucc.gats.trinity.service.ApplyInfoService;
import edu.zucc.gats.trinity.service.CollegeService;
import edu.zucc.gats.trinity.service.DomainService;
import edu.zucc.gats.trinity.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    


}
