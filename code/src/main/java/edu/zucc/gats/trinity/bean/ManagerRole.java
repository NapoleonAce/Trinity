package edu.zucc.gats.trinity.bean;

public class ManagerRole {
    private int id;
    private String manaCode;
    private int roleId;


    @Override
    public String toString() {
        return "ManagerRole{" +
                "id=" + id +
                ", manaCode='" + manaCode + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManaCode() {
        return manaCode;
    }

    public void setManaCode(String manaCode) {
        this.manaCode = manaCode;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
