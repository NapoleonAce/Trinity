package edu.zucc.gats.trinity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignIn {
    @GetMapping("/test")
    public String testAuth(){
        return "test Authority";
    }
}
