<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生文章界面</title>
    <script type="text/javascript" src="<%=path%>/resource/js/ajax.js?version=20171130112"></script>
    <script type="text/javascript" src="<%=path%>/resource/js/articleList.js?version=20171130120"></script>
</head>

<body>
<input type="hidden" value="${student.id}" id="studentId">
<center>
    <div>
        <h1>文章列表</h1>
        <table id="tableArea">
            <thead>
            <tr>
                <td>封面</td>
                <td>标题</td>
                <td>发布人</td>
                <td>删除</td>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach var="articleDetail" items="${articleDetailList}">

                <tr>
                    <td><img src="<%=path%>/${articleDetail.picture}" alt="">
                    </td>
                    <td><a href="articleList.do?articleId=${articleDetail.id}">${articleDetail.title}</a></td>


                    <td>${articleDetail.name}</td>
                    <td>
                        <c:if test="${articleDetail.studentId == student.id || sessionScope.student.role == 1}">
                        <input type="button" value="delete"
                               onclick="deleteArticle(${articleDetail.id},${articleDetail.studentId},this)">
                        </c:if>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="addArticle.jsp">发布文章</a>
    </div>
    <div class="page">
        <page:page pager="${requestScope.pager}"/>
    </div>
    <a href="index.jsp">返回主页</a>
</center>
</body>
</html>
