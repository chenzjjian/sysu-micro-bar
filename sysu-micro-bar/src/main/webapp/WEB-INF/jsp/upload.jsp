<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/18 0018
  Time: 下午 12:49
  To change this template use File | Settings | File Templates.
--%>s
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Upload File Request Page</title>
</head>
<body>

<form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="file"><br />
    Content: <input type="text" name="content"><br /> <br />
    <input type="submit" value="Upload"> Press here to upload the file!
</form>

</body>
</html>