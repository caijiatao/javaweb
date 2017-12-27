<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页</title>
</head>
<body>
<div>
    <center>
        <h1>注册</h1>
        <form action="register.do" method="post">
            <h2>user：<input type="text" name="name"></h2>
            </h2>password ： <input id="password" type="password" name="password"></h2>
            </h2>confirm password ： <input type="password" id="confirmPassword"></h2>
            </h2>school : <input type="text" name="school"></h2>
            <input type="submit" value="添加学生"/>

        </form>
    </center>
</div>
</body>
</html>
