<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作流系统</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript">
    $(function(){
      var basepath = "<%=basePath%>";
      $("#form1 #button1").click(function(){
        console.log("=================");
        var name = $("#form1 #name").val();
        var password = $("#form1 #password").val();
        var vercode = $("#form1 #vercode").val();
        console.log(name.length);

        if(name.length==0 || password.length==0){

        }else{
          console.log(name);
          console.log(password);
          $.ajax({
          url:basepath + "check",
          type:'POST',
          dataType:'json',
          data:{name:name,password:password,vercode:vercode},
          success:function(data){
            if(data.statusCode==1){
              console.log("===========");
              var url = basepath + 'indexOfManager';
              $("#form1").attr('action',url);
              $("#form1").submit();
            }else if(data.statusCode==2){
              console.log("===========");
              var url = basepath + 'indexOfEmployee';
              $("#form1").attr('action',url);
              $("#form1").submit();
            }else{
              console.log("===========");
              location.reload();
            }
          }
        })
        }
      });
    });
  </script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">请输入用户名和密码来登录</h3>
  </div>
<form id="form1" method="post" class="form-horizontal">

  <div class="row">
    <div class="col-sm-12 text-info text-center">
    </div>
  </div>
  <div class="form-group">
    <label for="name" class="col-sm-3 control-label">用户名</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="name"
    name="manager.name" placeholder="用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-3 control-label">密码</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="password"
    name="manager.pass" placeholder="密码">
    </div>
  </div>

  <div class="form-group">
    <label for="vercode" class="col-sm-3 control-label">验证码</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="vercode"
             name="vercode" placeholder="验证码">
    </div>
    <div class="col-sm-3">
      <img name="d" src="authImg">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="button" class="btn btn-default" id="button1">提交</button>
      <button type="reset" class="btn btn-danger">重填</button>
    </div>
  </div>

</form>

</div>
</div>
</body>
</html>
