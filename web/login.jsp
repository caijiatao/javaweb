<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页</title>
</head>
<body>
<center>
    <div>
        <h1>Login</h1>
        <form action="login.do" method="post">
            name：<input type="text" name="name">
            password：<input type="password" name="password">
            <input type="submit" value="登录"/>
        </form>
    </div>
    <h1>没有账号？<a href="register.jsp">注册</a></h1>
</center>
</body>
</html>
