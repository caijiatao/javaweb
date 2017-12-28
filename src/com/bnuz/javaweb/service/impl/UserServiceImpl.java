package com.bnuz.javaweb.service.impl;


import com.bnuz.javaweb.bean.Student;
import com.bnuz.javaweb.dao.UserDao;
import com.bnuz.javaweb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cai on 2017/11/15.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public boolean addStudent(String name, String password,String school) {
        return userDao.insertStudentInfo(name,password,school);
    }

    @Override
    public Student verifyStudentInfo(String name, String password) {
        return userDao.verifyStudentInfo(name,password);
    }


    @Override
    public List<Student> selectStudent(String name) {
        //如果没有日期
        return userDao.queryStudent(name);
    }



    @Override
    public boolean modifyStudentInfo(int studentId,String name,String school, String password) {
        return userDao.modifyStudentInfo(studentId,name,school,password);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return userDao.deleteStudent(studentId);
    }
}
