

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>查看本部门上个月的发薪</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
  <script type="text/javascript">
    function GenDeptTbody(data) {
      var tbody = $("#DeptTbody");
      for(var i in data){
        var data1 = data[i];
        var tr = $("<tr>");
        var td1 = $("<td>");
        var td2 = $("<td>");
        td1.html(data1.name);
        td2.html(data1.amount);
        tr.append(td1);
        tr.append(td2);
        tbody.append(tr);
      }
    }
    function init() {
      UseAjax("<%=basePath%>" + "/manager/viewDeptSal",'POST',{},function (data) {
        if(data.statusCode == 0){
          GenDeptTbody(data.data);
        }
      });
    }
  </script>
</head>
<body onload="init()">
<%@include file="../header.jsp"%>
<%@include file="mgrheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">
     查看上个月部门的全部工资</h3>
  </div>
  <div class="panel-body">
    <table class="table table-hover table-striped">
      <thead>
        <tr>
          <th>员工名</th>
          <th>薪水</th>
        </tr>
      </thead>
      <tbody id="DeptTbody">

      </tbody>
    </table>
  </div>
</div>
</div>
</body>
</html>