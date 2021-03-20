

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>增加新员工</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
  <script type="text/javascript">
      $(function () {
            $("#formEmp #addEmp").click(function () {
                var url = "<%=basePath%>" + "/manager/addEmp";
                var name = $("#formEmp #emp_name").val();
                var pass = $("#formEmp #emp_pass").val();
                var salary = $("#formEmp #emp_salary").val();
                var email = $("#formEmp #emp_email").val();
                UseAjax(url, 'POST',{"name":name,"password":pass,"salary":salary,"email":email},function (data) {
                  if(data.statusCode == 0){
                    alert(data.message);
                    location.reload();
                  }else{
                    alert(data.message);
                  }
                });
            });
      });
  </script>


</head>
<body>
<%@include file="../header.jsp"%> 
<%@include file="mgrheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">
    请您输入新员工的资料</h3>
  </div>
<form id="formEmp" class="form-horizontal">
  <div class="form-group">
    <label for="emp_name" class="col-sm-3 control-label">员工用户名</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="emp_name"
        name="emp_name" placeholder="员工用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="emp_pass" class="col-sm-3 control-label">员工密码</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="emp_pass"
        name="emp_pass" placeholder="员工密码">
    </div>
  </div>
  <div class="form-group">
    <label for="emp_salary" class="col-sm-3 control-label">员工月薪</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="emp_salary"
        name="emp_salary" placeholder="员工月薪">
    </div>
  </div>
  <div class="form-group">
    <label for="emp_email" class="col-sm-3 control-label">员工月薪</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="emp_email"
             name="emp_email" placeholder="员工邮箱">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
	  <button type="button" id="addEmp" class="btn btn-default">添加新员工</button>
      <button type="reset" class="btn btn-danger">重新输入</button>
    </div>
  </div>
</form>
</div>
</div>
</body>
</html>
