package edu.zucc.gats.trinity.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Manager {
    private String manCode;
    private String manName;
    private String manLevel;
    private String manPassword;

    @Override
    public String toString() {
        return "manager{" +
                "manCode='" + manCode + '\'' +
                ", manName='" + manName + '\'' +
                ", manLevel='" + manLevel + '\'' +
                ", manPassword='" + manPassword + '\'' +
                '}';
    }

    public String getManCode() {
        return manCode;
    }

    public void setManCode(String manCode) {
        this.manCode = manCode;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getManLevel() {
        return manLevel;
    }

    public void setManLevel(String manLevel) {
        this.manLevel = manLevel;
    }

    @JsonIgnore
    public String getManPassword() {
        return manPassword;
    }

    public void setManPassword(String manPassword) {
        this.manPassword = manPassword;
    }

    //userdetail 部分


}
