<%--
  Created by IntelliJ IDEA.
  User: wangzy
  Date: 2018/4/27
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <img src="<%=basePath%>/image/0.jpg" style="width: 250px" class="media-object">
</body>
</html>
