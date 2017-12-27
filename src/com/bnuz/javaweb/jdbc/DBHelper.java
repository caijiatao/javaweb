package com.bnuz.javaweb.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caijiatao
 * @data 2017/12/25 下午10:33
 */

/*
数据库操作javabean通用类
 */
public class DBHelper {
    private Connection connection = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/javaweb";
    private static final String username = "root";
    private static final String password = "root";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得jdbc一个连接
     *
     * @return
     */
    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库相关的资源，如果为null则无需关闭
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对参数进行设置
     * @param preparedStatement
     * @param objects
     * @return
     * @throws SQLException
     */
    public static PreparedStatement setSqlParam(PreparedStatement preparedStatement,Object... objects) throws SQLException {
        for(int i = 1;i <= objects.length;i++){
            preparedStatement.setObject(i,objects[i-1]);
        }
        return preparedStatement;
    }

    /**
     * 获得结果集的list
     * @param resultSet
     * @param clazz
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static List dataToList(ResultSet resultSet , Class clazz,Connection connection)
            throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List dataList = new ArrayList();
        while (resultSet != null && resultSet.next()){
            //获得该对象实例
            Object object = clazz.newInstance();
            //获得结果集的结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获得所有列名和值
            for(int i = 1;i <= resultSetMetaData.getColumnCount();i++){
                String colName = resultSetMetaData.getColumnName(i);
                //setxxx的方法
                char[] chars = colName.toCharArray();
                chars[0] -= 32;
                String methodName = "set" + String.valueOf(chars);
                //反射获得setxxx方法
                Object colValue = resultSet.getObject(colName);
                Method method = object.getClass().getMethod(methodName,colValue.getClass());//第一个参数是方法名，第二个参数是参数。
                //调用方法设置实体的属性
                method.invoke(object,colValue);
            }
            dataList.add(object);
        }
        resultSet.close();
        connection.close();
        return dataList;
    }

    /**
     * 将string的日期转化成sql 包里面的日期
     * @param date
     * @return
     */
    public static Date stringToSqlDate(String date){
        Date sqlDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = simpleDateFormat.parse(date);
            sqlDate = new Date(d.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * 返回查询结果集
     * @param sql sql语句
     * @param objects preparedStatement里面需要设置的参数
     * @return
     */
    public ResultSet executeQuery(String sql,Connection connection,Object ...objects){
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setSqlParam(preparedStatement,objects);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public List executeQuery(String sql,Class clazz ,Connection connection, Object ...objects){

        ResultSet resultSet = null;
        List list = null;
        try {
            resultSet = executeQuery(sql,connection,objects);
            list = dataToList(resultSet,clazz,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet,null,connection);
        }

        return list;
    }

    /**
     * 执行更新插入删除语句
     * @param sql
     * @param objects
     * @return
     */
    public boolean executeUpdateSql(String sql,Object ...objects){
        connection = getConnection();
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setSqlParam(preparedStatement,objects);
            result = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    /**
     * 通过表名生成统计数据条数的sql
     * @param formName
     * @return
     */
    public String generateCountSql(String formName){
        String countSQL = "SELECT count(*) FROM " + formName;
        return countSQL;
    }

    /**
     * 生成统计数据条数的条件Sql
     * @param conditions
     * @return
     */
    public String generateCountConditionSql(String countSQL,String ...conditions){
        countSQL = conditions.length > 0 ? countSQL + " WHERE " : countSQL;
        for(String condition : conditions){
            countSQL.concat(condition + " = ? ");
        }
        System.out.println(countSQL);
        return countSQL;
    }
}
