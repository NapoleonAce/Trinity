package edu.zucc.gats.trinity.bean;



public class Manager  {
    private String code;
    private String name;
    private String password;

    public Manager(){

    }
    public Manager(String code, String name, String password) {
        this.code = code;
        this.name = name;
        this.password = password;
    }

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
