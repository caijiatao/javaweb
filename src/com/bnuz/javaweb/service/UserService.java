package com.bnuz.javaweb.service;

import com.bnuz.javaweb.bean.Student;

import java.util.List;

/**
 * Created by cai on 2017/11/15.
 */
public interface UserService {

    /**
     * 插入学生
     *
     * @param name 用户名
     * @param password 密码
     * @return 插入成功返回true
     */
    public boolean addStudent(String name, String password,String school);

    /**
     * 验证学生登陆信息
     *
     * @param name 用户名
     * @param password 密码
     * @return 验证信息有效则返回学生实体
     */
    public Student verifyStudentInfo(String name, String password);

    /**
     * 查询符合搜索条件的学生
     * @param name 搜索的名字
     * @return 返回符合条件的学生列表
     */
    public List<Student> selectStudent(String name);

    /**
     * 修改学生信息
     * @param studentId 学生id
     * @param name 修改后学生姓名
     * @param school 修改后的学校
     * @param password 修改后的密码
     * @return 修改成功为true
     */
    public boolean modifyStudentInfo(int studentId,String name,String school,String password);

    /**
     * 删除学生
     * @param studentId 要删除学生的id
     * @return 删除成功为true
     */
    public boolean deleteStudent(int studentId);
}
