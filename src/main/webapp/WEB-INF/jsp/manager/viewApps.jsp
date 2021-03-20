<%@ page import="po.Manager" %>

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=GBK">
   <title>处理申请</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
  <script type="text/javascript">
    function GenAppTbody(data) {
        var tbody = $("#AppsTbody");
        for(var i in data){
          var data1 = data[i];
          var tr = $("<tr>");
          var td1 = $("<td>");
          var td2 = $("<td>");
          var td3 = $("<td>");
          var td4 = $("<td>");
          var td5 = $("<td>");
          td1.html(data1.name);
          td2.html(data1.attendType1);
          td3.html(data1.attendType2);
          td4.html(data1.reason);
          td5.html("<button  class='applicationPass'>" +
                  "\t\t\t  通过</button>&nbsp;&nbsp;<button href='#' class='applicationRefuse'"  + ">" +
                  "\t\t\t  拒绝</button>");
          td5.attr("id",data1.id);
          tr.append(td1);
          tr.append(td2);
          tr.append(td3);
          tr.append(td4);
          tr.append(td5);
          tbody.append(tr);
        }
        $(".applicationPass").click(function () {
            console.log("aaaa");
            var id = $(this).parent().attr("id");
            var data1 = {"id":id,"result":"pass"};
            UseAjax("<%=basePath%>" + "/application/result",'POST',data1,function (data) {
              if(data.statusCode == 0){
                alert(data.message);
                location.reload();
              }else{
                alert(data.message);
              }
            });
        });
      $(".applicationRefuse").click(function () {
        var id = $(this).parent("<td>").attr("id");
        var data1 = {"id":id,"result":"refuse"};
        UseAjax("<%=basePath%>" + "/application/result",'POST',data1,function (data) {
          if(data.statusCode == 0){
            alert(data.message);
            location.reload();
          }else{
            alert(data.message);
          }
        });
      });
    }
    function init() {
      var url = "<%=basePath%>" + "/application/viewEmpApps"
      var type = 'POST';
      UseAjax(url,type,{},function (data) {
        if(data.statusCode == 0){
          GenAppTbody(data.data);
        }
      })
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
    当前用户：<%=((Manager)session.getAttribute("user")).getName()%></h3>
  </div>
  <div class="panel-body">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>员工名</th>
          <th>缺勤类型</th>
          <th>申请类型</th>
          <th>理由</th>
          <th></th>
        </tr>
      </thead>
      <tbody id="AppsTbody">

      </tbody>
    </table>
  </div>
</div>
</div>
</body>
</html>