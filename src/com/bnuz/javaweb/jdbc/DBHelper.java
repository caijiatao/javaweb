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
     * @return 数据库链接
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
     * @param resultSet 查询结果集
     * @param preparedStatement
     * @param connection
     */
    public static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
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
     * @param objects 设置preparedStatement的参数，按顺序传入
     * @return 设置好参数的preparedstatement
     * @throws SQLException sql异常暂时不作任何处理
     */
    public static PreparedStatement setSqlParam(PreparedStatement preparedStatement,Object... objects) throws SQLException {
        for(int i = 1;i <= objects.length;i++){
            preparedStatement.setObject(i,objects[i-1]);
        }
        return preparedStatement;
    }

    /**
     * 获得结果集的list
     * @param resultSet 传入要转化的数据集
     * @param clazz 传入转化成数据集的类
     * @return 返回结果转化的list
     * @throws SQLException sql异常
     * @throws IllegalAccessException 不合法方法
     * @throws InstantiationException 实例化异常
     * @throws NoSuchMethodException 反射没有对应方法
     * @throws InvocationTargetException 调用异常
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
        closeAll(resultSet,null,connection);
        return dataList;
    }

    /**
     * 将string的日期转化成sql 包里面的日期
     * @param date 日期
     * @return sql类型的日期
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
     * @return 查询的结果
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

    /**
     * 查询方法重载
     * @param sql 要执行的sql
     * @param clazz 返回list的数据类型
     * @param connection 数据库链接
     * @param objects preparestatement设置的参数
     * @return 返回查询的list
     */
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
     * @param sql 更新的sql
     * @param objects 参数列表
     * @return 更新成功为true
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
     * @param formName 表名
     * @return 返回生成的计算数据的语句
     */
    public String generateCountSql(String formName){
        String countSQL = "SELECT count(*) FROM " + formName;
        return countSQL;
    }

    /**
     * 生成统计数据条数的条件Sql
     * @param conditions sql拼接where
     * @return 拼接好的sql语句
     */
    public String generateCountConditionSql(String countSQL,String ...conditions){
        countSQL = conditions.length > 0 ? countSQL + " WHERE " : countSQL;
        for(String condition : conditions){
            countSQL += condition + " = ? ";
        }
        return countSQL;
    }
}
