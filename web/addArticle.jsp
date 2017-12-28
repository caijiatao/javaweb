<%--
  Created by IntelliJ IDEA.
  User: cai
  Date: 2017/12/25
  Time: 下午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发表文章页面</title>
</head>
<body>

<center>
    <form id="" action="publishArticle.do" enctype="multipart/form-data" method="post">

        <table id="uploadTable">
            <tr>
                <td>标题:<input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>content : <textarea rows="5" cols="50" name="content"></textarea></td>
            </tr>
            <tr>
                <td><input type="file" name="picture" value="添加文章封面"/></td>
            </tr>
        </table>
        <input type="submit" value="提交">

    </form>
</center>
</body>
</html>
