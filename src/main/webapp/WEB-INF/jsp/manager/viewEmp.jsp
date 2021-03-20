

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>查看本部门全部员工</title>
    <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
    <script type="text/javascript">
        function GenEmployeeTbody(data) {
            var tbody = $("#EmployeeTbody");
            for(var i in data){
                var data1 = data[i];
                var tr = $("<tr>");
                var td1 = $("<td>");
                var td2 = $("<td>");
                var td3 = $("<td>");
                td1.html(data1.name);
                td2.html(data1.password);
                td3.html(data1.salary);
                tr.append(td1);
                tr.append(td2);
                tr.append(td3);
                tbody.append(tr);
            }
        }
        function init() {
          UseAjax("<%=basePath%>" + "/manager/viewDptEmployee",'POST',{},function (data) {
            if(data.statusCode == 0){
              GenEmployeeTbody(data.data);
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
     查看部门的全部员工</h3>
  </div>
  <div class="panel-body">
    <table class="table table-hover table-striped">
      <thead>
        <tr>
          <th>员工名</th>
          <th>密码</th>
          <th>工资</th>
        </tr>
      </thead >
      <tbody id="EmployeeTbody">

      </tbody>
    </table>
  </div>
</div>
</div>
</body>
</html>