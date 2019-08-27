package edu.zucc.gats.trinity.bean;

public class ManagerRole {
    private int id;
    private String managerCode;
    private int roleId;

    public ManagerRole(int id, String managerCode, int roleId) {
        this.id = id;
        this.managerCode = managerCode;
        this.roleId = roleId;
    }

    public ManagerRole(String managerCode, int roleId) {
        this.managerCode = managerCode;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ManagerRole{" +
                "id=" + id +
                ", manaCode='" + managerCode + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String manaCode) {
        this.managerCode = manaCode;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
