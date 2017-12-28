<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改文章页面</title>
    <script type="text/javascript" src="resource/js/ajax.js"></script>
    <script type="text/javascript" src="resource/js/articleDetail.js"></script>
</head>
<body>


<center>
    <div>
        <h1>标题：${articleDetail.title}</h1>
        <h2>发布人：${articleDetail.name}</h2>
        内容：<textarea id="content"readonly rows="10" cols="100">${articleDetail.content}</textarea>
        <c:if test="${sessionScope.student.id == articleDetail.studentId || sessionScope.student.role == 1}">
            <input type="button" onclick="modifyArticle()" value="编辑文章">
            <input type="button" value="提交修改" onclick="submitModify('${articleDetail.content}',${articleDetail.id})"/>
        </c:if>
    </div>
</center>
</body>
</html>
