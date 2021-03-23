<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Java EE简单工作流系统</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
  <div class="jumbotron">
    <h1>你没有权限，可以选择先登录</h1>
    <p><a class="btn btn-primary btn-lg" href="<%=basePath%>/login" role="button">登录系统</a></p>
  </div>
</div>
</body>
</html>