package edu.zucc.gats.trinity.controller;


import edu.zucc.gats.trinity.service.ApplyInfoService;
import edu.zucc.gats.trinity.service.CollegeService;
import edu.zucc.gats.trinity.service.DomainService;
import edu.zucc.gats.trinity.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/colMan")
public class ColManController {
    @Autowired
    CollegeService collegeService;
    @Autowired
    DomainService domainService;
    @Autowired
    EnrollService enrollService;
    @Autowired
    ApplyInfoService applyInfoService;


}
