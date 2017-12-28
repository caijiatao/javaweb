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
public class UserDao extends BaseDao {

    /**
     * 插入新学生登陆信息
     *
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
     */
    public Student verifyStudentInfo(String name, String password) {
        Connection connection = dbHelper.getConnection();
        Student student = null;
        String sql = "SELECT id,name,password,role,school " +
                "FROM T_Student " +
                "WHERE " +
                "name = ? AND password = ?";
        List list = dbHelper.executeQuery(sql, Student.class, connection, name, password);
        student = list.size() > 0 ? (Student) list.get(0) : null;
        return student;
    }

    /**
     * 查询学生列表
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

    /**
     * 修改学生账号信息
     *
     * @param studentId 学生id
     * @return 修改成功为true或者失败false
     */
    public boolean modifyStudentInfo(int studentId, String name, String school, String password) {
        String sql = "UPDATE T_Student SET name = ? ,school = ? , password = ? WHERE id = ?";
        System.out.println(school);
        return dbHelper.executeUpdateSql(sql, name, school, password, studentId);
    }

    public boolean deleteStudent(int studentId){
        String sql = "DELETE FROM T_Student WHERE id = ? ";
        return dbHelper.executeUpdateSql(sql,studentId);
    }


}
