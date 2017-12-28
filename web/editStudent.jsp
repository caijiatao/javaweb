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
    <title>修改学生信息界面</title>
</head>
<body>
<center>
    <h1>修改信息</h1>
    <form action="editStudentInfo.do">
        <h1>姓名：<input type="text" name="name" value="${sessionScope.student.name}"/></h1>
        <h1>
            学校：<input type="text" name="school" value="${sessionScope.student.school}"/>

        </h1>
        <h1> 密码：<input type="text" name="password" value="${sessionScope.student.password}"/></h1>
        <input type="submit" value="保存修改">
    </form>
    <a href="index.jsp">返回主页</a>
</center>
</body>
</html>
