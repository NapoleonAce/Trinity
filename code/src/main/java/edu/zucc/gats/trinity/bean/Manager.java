package edu.zucc.gats.trinity.bean;



public class Manager  {
    private String code;
    private String name;
    private String password;
    private int roleId;
    private int permId;


    public Manager(String code, String name, String password, int roleId, int permId) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.permId = permId;
    }

    public int getPermId() {
        return permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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
                ", roleId=" + roleId +
                ", permId=" + permId +
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
