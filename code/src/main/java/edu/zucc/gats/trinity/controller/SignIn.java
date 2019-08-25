package edu.zucc.gats.trinity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignIn {
    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin";
    }

    @GetMapping("/student/hello")
    public String student(){
        return "hello Student";
    }

    @GetMapping("/college/hello")
    public String college(){
        return "hello College";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello ";
    }
}
