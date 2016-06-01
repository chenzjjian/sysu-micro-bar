<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/19 0019
  Time: 上午 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <title>Upload File Request Page</title>
</head>
<body>
<h2>上传多个文件 实例</h2>
<form action="uploadMultipleFile" method="post"
      enctype="multipart/form-data">
    <p>
        选择文件:<input type="file" name="file">
    <p>
        选择文件:<input type="file" name="file">
    <p>
        选择文件:<input type="file" name="file">
    <p>
        <input type="submit" value="提交">
</form>
</body>