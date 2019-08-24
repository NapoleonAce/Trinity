package edu.zucc.gats.trinity.bean;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class manager implements UserDetails {
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

    public String getManPassword() {
        return manPassword;
    }

    public void setManPassword(String manPassword) {
        this.manPassword = manPassword;
    }

    //userdetail 部分
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
