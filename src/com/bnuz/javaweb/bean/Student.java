package com.bnuz.javaweb.bean;

/**
 * @author caijiatao
 * @data 2017/12/25 下午10:32
 */
public class Student {
    //用户id
    private Integer id;
    //用户名
    private String name;
    //学校名字
    private String school;
    //用户角色
    private Integer role;
    //用户密码
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
