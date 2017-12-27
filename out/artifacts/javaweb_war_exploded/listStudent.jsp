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
        </tr>
        </thead>
        <tbody>
        <% for (Student student : students) {

        %>
        <tr>
            <td><a href="queryPictureList.do?studentId=<%=student.getId()%>"><%=student.getName()%>
            </a></td>
            <td><%=student.getSchool()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</center>
</body>
</html>
