
<%@ page import="po.Employee" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=GBK">
   <title>查看自己的非正常出勤</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript">
      function GenTBody(data) {
        console.log(data);
        var tbody = $("#TBodyOfUnAttend");
        for(var i in data){
          var tr1 = $("<tr>");
          var td1 = $("<td>");
          var td2 = $("<td>");
          var td3 = $("<td>");
          var td4 = $("<td>");
          var attend = data[i];
          tr1.attr("id",attend.id);
          td1.html(attend.dutyDate);
          td2.html(attend.attendType);
          td3.html(attend.punchTime)
          td4.html("<a href='<%=basePath%>/employee/appChange?id=" +  attend.id + "'>申请改变</a>");
          tr1.append(td1);
          tr1.append(td2);
          tr1.append(td3);
          tr1.append(td4);
          tbody.append(tr1);

        }

      }
      function ShowUnAttend() {
          $.ajax({
            url: "<%=basePath%>" + "/employee/viewUnPunch",
            type: 'POST',
            dataType: 'json',
            data:{},
            success: function (data) {
                if(data.statusCode == 0){
                  GenTBody(data.data);
                }else{
                   alert(data.message);
                }
            }
          })
      }
  </script>
</head>
<body onload="ShowUnAttend()">
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">
    当前用户：<%=((Employee)session.getAttribute("user")).getName()%></h3>
  </div>
  <div class="panel-body">
    <table class="table table-hover">
      <thead>
        <tr>
          <th>打卡日期</th>
          <th>异动名称</th>
          <th>打卡时间</th>
          <th></th>
        </tr>
      </thead>
      <tbody id="TBodyOfUnAttend">

      </tbody>
    </table>
  </div>
</div>
</div>
</body>
</html>