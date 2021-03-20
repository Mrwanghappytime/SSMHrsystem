<%@ page import="po.Employee" %>
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
   <title>员工查看自己的工资</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
  <script type="text/javascript">
    function init() {
        var url = "<%=basePath%>" + "/payment/getEmployeePayment";
        var type = 'POST';
        var data1 = {};
        UseAjax(url,type,data1,function (data) {
          if(data.statusCode == 0){
            var data2 = data.data;
            var tbody = $("#SalaryBody");
            for(var i in data2) {
              var payment = data2[i];
              var tr = $("<tr>");
              var td1 = $("<td>");
              var td2 = $("<td>");
              td1.html(payment.month);
              td2.html(payment.amount);
              tr.append(td1);
              tr.append(td2);
              tbody.append(tr);
            }
          }else{
            alert(data.message);
          }
        });
    }
  </script>
</head>
<body onload="init()">
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">
    当前用户：<%=((Employee)session.getAttribute("user")).getName()%></h3>
  </div>
  <div class="panel-body">
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>发薪月份</th>
          <th>薪水</th>
        </tr>
      </thead>
      <tbody id="SalaryBody">

      </tbody>
    </table>
  </div>
</div>
</div>
</body>
</html>