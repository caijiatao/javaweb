package com.bnuz.javaweb.dao;

import com.bnuz.javaweb.bean.Student;
import com.bnuz.javaweb.jdbc.DBHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cai on 2017/11/14.
 */
public class UserDao extends BaseDao{


    /**
     * 添加文章
     *
     * @param title
     * @param studentId
     * @param content
     * @return
     */
    public boolean addArticle(String title, Integer studentId, String content) {
        Connection connection = dbHelper.getConnection();
        int result = 0;
        String sql = "INSERT INTO article (studentId,title,content) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            dbHelper.setSqlParam(preparedStatement, studentId, title, content);
            result = preparedStatement.executeUpdate();//返回结果是插入了几条
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    /**
     * 插入新学生登陆信息
     *
     * @return
     */
    public boolean insertStudentInfo(String name, String password, String school) {
        Connection connection = dbHelper.getConnection();
        String sql = "INSERT INTO T_Student (name,password,school,role) values (?,?,?,?)";
        boolean result = true;
        result = dbHelper.executeUpdateSql(sql, name, password, school, 0);
        dbHelper.closeAll(null, null, connection);
        return result;
    }

    /**
     * 验证登陆信息
     *
     * @param name     用户名
     * @param password 密码
     * @return
     */
    public Student verifyStudentInfo(String name, String password) {
        Connection connection = dbHelper.getConnection();
        Student student = null;
        String sql = "SELECT id,name,password " +
                "FROM T_Student " +
                "WHERE " +
                "name = ? AND password = ?";
        List list = dbHelper.executeQuery(sql, Student.class, connection, name, password);
        student = list.size() > 0 ? (Student) list.get(0) : null;
        return student;
    }

    /**
     * 查询学生列表
     *
     * @param name
     * @return
     */
    public List<Student> queryStudent(String name) {
        String sql = null;
        ResultSet resultSet = null;
        name = name == null ? "%%" : "%" + name + "%";
        sql = "SELECT id,name,school " +
                "FROM T_Student WHERE name LIKE ? " +
                "order by id";
        Connection connection = dbHelper.getConnection();
        List<Student> students = dbHelper.executeQuery(sql, Student.class, connection, name);
        return students;
    }


//
//    /**
//     * 通过学生ID来查询文章
//     * @param studentId
//     * @return
//     */
//    public List<ArticleDetail> queryArticleByStudentId(Integer studentId){
//        List<ArticleDetail> articleDetails = new ArrayList<>();
//        return articleDetails;
//    }
//
//    /**
//     * 删除对应文章的id
//     * @param articleId
//     * @return
//     */
//    public boolean deleteArticle(int articleId){
//        int result = 0;
//        String sql = "DELETE FROM article WHERE id = ?";
//        Connection connection = JDBC.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            JDBC.setSqlParam(preparedStatement,articleId);
//            result = preparedStatement.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result > 0? true : false;
//    }
//
//    /**
//     * 修改文章内容
//     * @return
//     */
//    public boolean modifyArticle(int articleId,String content){
//        String sql = "UPDATE article SET content = ? WHERE id = ?";
//        return JDBC.executeUpdateSql(sql,content,articleId);
//    }
//
//    /**
//     * 修改学生账号信息
//     * @param studentId
//     * @return
//     */
//    public boolean modifyStudentInfo(int studentId,String password){
//        String sql = "UPDATE student SET password = ? WHERE id = ?";
//        return JDBC.executeUpdateSql(sql,password,studentId);
//    }
}
