<%@ page import="po.Employee" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电子打卡</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript">
    var basepath = "<%=basePath%>";
    $(function () {
      $("#punchButton").click(function(){
          $.ajax({
            url:basepath + "/employee/punch",
            type:'POST',
            dataType:'json',
            data:{},
            success:function (data) {
              if(data.statusCode == 0){
                alert("打卡成功");
              }else{
                alert("打卡失败");
              }
            }
          });
      });
    });
  </script>
</head>
<body>
<%@include file="../header.jsp"%> 
<%@include file="empheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">电子打卡系统</h3>
  </div>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <div class="row">
    <div class="col-sm-offset-5 col-sm-2">
      <%=((Employee)session.getAttribute("user")).getName()%>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-offset-5 col-sm-2">
<!-- 当punchIsValid为1、3时，可上班打卡 -->
        <a id="punchButton"
           class="btn btn-default">打卡
        </a>
	</div>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</div>
</div>
</body>
</html>
