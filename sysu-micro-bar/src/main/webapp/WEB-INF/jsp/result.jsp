<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/18 0018
  Time: 下午 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>上传结果</title>
</head>
<body>
    <img src="${fileUrl }" />
</body>
</html>
