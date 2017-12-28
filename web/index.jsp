<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/26
  Time: 下午4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <center>
        <h1>Hello , ${student.name}</h1>
        <h2>School : ${student.school}</h2>
        <h1><a href="editStudent.jsp">修改个人信息</a></h1>
        <h1><a href="login.jsp">退出登陆</a></h1>
        <h2><a href="queryStudent.do">学生列表界面</a></h2>
        <h2><a href="articleList.do">学生文章界面</a></h2>
    </center>
</body>
</html>
