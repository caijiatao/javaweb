package com.bnuz.javaweb.service;

import com.bnuz.javaweb.bean.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cai on 2017/11/15.
 */
public interface UserService {

    /**
     * 插入学生
     *
     * @param name
     * @param password
     * @return
     */
    public boolean addStudent(String name, String password,String school);

    /**
     * 验证学生登陆信息
     *
     * @param name
     * @param password
     * @return
     */
    public Student verifyStudentInfo(String name, String password);

    /**
     * 查询符合搜索条件的学生
     * @param name 搜索的名字
     * @return
     */
    public List<Student> selectStudent(String name);


//    /**
//     * 发布文章
//     *
//     * @param studentId
//     * @param title
//     * @param content
//     * @return
//     */
//    public boolean publishArticle(Integer studentId, String title, String content);
//
//    /**
//     * 通过学生id查询文章
//     *
//     * @param studentId
//     * @return
//     */
//    public List<ArticleDetail> queryArticleByStudentId(Integer studentId);
//
//    /**
//     * 查询某篇文章的细节
//     *
//     * @param id
//     * @return
//     */
//    public List<ArticleDetail> queryArticleDetail(Integer id, Integer currentPage, Integer pageSize, HttpServletRequest request);
//
//
//    /**
//     * 删除文章
//     *
//     * @param articleId
//     * @return
//     */
//    public boolean deleteArticle(int articleId);
//
//    /**
//     * 修改文章
//     *
//     * @return
//     */
//    public boolean modifyArticle(int articleId, String content);
//
//    /**
//     * 修改学生信息
//     *
//     * @return
//     */
//    public boolean modifyStudentInfo(int studentId, String password);
}
