<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学生信息界面</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<center>
    <h1>添加学生</h1>
    <form action="register.do" method="post">
        <h2>user：<input type="text" name="name"></h2>
        </h2>password ： <input id="password" type="password" name="password"></h2>
        </h2>school : <input type="text" name="school"></h2>
        <input type="submit" value="添加学生"/>
    </form>
</center>
</body>
</html>
