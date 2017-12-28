<%@ page import="com.bnuz.javaweb.bean.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Student studentInfo = (Student) request.getSession().getAttribute("student");
    System.out.println(studentInfo.getRole());
%>
<html>
<head>
    <title>学生列表界面</title>

</head>
<body>
<center>
    <form action="queryStudent.do" method="get">
        输入要查询学生的名字 ：<input name="name" type="text"/>
        <input type="submit" value="查询"/>
    </form>
    <table>
        <thead>
        <tr>
            <td>姓名</td>
            <td>学校</td>
            <c:if test="${sessionScope.student.role == 1}">
                <td>删除用户</td>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
        <tr>
            <td><a href="queryPictureList.do?studentId=${student.id}">${student.name}
            </a></td>
            <td>${student.school}</td>
            <c:if test="${sessionScope.student.role == 1 || sessionScope.student.id == student.id}">
                <td><a href="deleteUser.do?studentId=${student.id}">删除</a></td>
            </c:if>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>    <a href="addStudent.jsp">添加学生</a>
    </h2>
    <h2>    <a href="index.jsp">返回主页</a>
    </h2>
</center>
</body>
</html>
