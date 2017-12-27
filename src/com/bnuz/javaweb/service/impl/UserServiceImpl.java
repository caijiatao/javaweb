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
//    @Override
//    public boolean publishArticle(Integer studentId, String title, String content) {
//        return userDao.addArticle(title,studentId,content);
//    }
//
//    @Override
//    public List<ArticleDetail> queryArticleByStudentId(Integer studentId) {
//        return null;
//    }
//
//    @Override
//    public List<ArticleDetail> queryArticleDetail(Integer id, Integer currentPage, Integer pageSize, HttpServletRequest request) {
//        return userDao.queryArticleDetail(id,currentPage,pageSize,request);
//    }
//
//    @Override
//    public boolean deleteArticle(int articleId) {
//        return userDao.deleteArticle(articleId);
//    }
//
//    @Override
//    public boolean modifyArticle(int articleId, String content) {
//        return userDao.modifyArticle(articleId,content);
//    }
//
//    @Override
//    public boolean modifyStudentInfo(int studentId, String password) {
//        return userDao.modifyStudentInfo(studentId,password);
//    }
}
