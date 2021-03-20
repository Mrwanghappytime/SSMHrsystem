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
  <title>提出异动申请</title>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-3.1.1.js"></script>
  <script type="text/javascript" src="<%=path%>/bootstrap/js/ajax.js"></script>
  <script type="text/javascript">
    $(function () {
      $("#form1 #appButton").click(function () {
          console.log("++++++++++++++++++");
          var url = "<%=basePath%>" + "/application/submitApp";
          var type = 'POST';
          var id = $("#form1 #type_id option:selected").attr("id");
          var opt = $("#type_id option:selected");
          console.log(opt);
          console.log(id);
          var appId = $("#appId").val();
          var reason = $("#reason").val();
          var data1 = {"reason":reason,"attendTypeId":id,"attendId":appId};
          UseAjax(url,type,data1,function (data) {
              alert(data.message);
          });
      });
    });
    function genTab(data) {
        var sel = $("#type_id");
        for(var i in data){
           var option = $("<option>");
           option.html(data[i].name);
           option.attr("id",data[i].id);
           sel.append(option);
        }
    }

    function GenSelectTab() {
        $.ajax({
          url: "<%=basePath%>" + "/application/getall",
          type: 'POST',
          dataType:'json',
          data:{ },
          success:function (data) {
              console.log(data);
              if(data.statusCode == 0){
                  genTab(data.data);
              }else{
              }
          }
        });
    }
  </script>
</head>
<body onload="GenSelectTab()">
<%@include file="../header.jsp"%>
<%@include file="empheader.jsp"%>
<div class="container">
<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">
      <%=((Employee)session.getAttribute("user")).getName()%>，请填写异动申请</h3>
  </div>
<form action="processApp" method="post" class="form-horizontal" id="form1">
<input type="hidden" id="appId" name="attId" value="<%=((Integer)session.getAttribute("appId"))%>"/>
<%--<s:if test="fieldErrors.size()>0">--%>
  <%--<div class="form-group">--%>
    <%--<div class="col-sm-12 text-danger text-center">--%>
    <%--<s:fielderror/>--%>
    <%--</div>--%>
  <%--</div>--%>
<%--</s:if>--%>
  <div class="form-group">
    <label for="type_id" class="col-sm-3 control-label">申请类别</label>
    <div class="col-sm-9">
      <select type="text" class="form-control" id="type_id"
        name="typeId" placeholder="用户名">
        <%--<s:iterator value="types" var="ty">--%>
          <%--<option value="${ty.id}">${ty.name}</option>--%>
        <%--</s:iterator>--%>
      </select>
    </div>
  </div>
  <div class="form-group">
    <label for="reason" class="col-sm-3 control-label">申请理由</label>
    <div class="col-sm-9">
      <textarea class="form-control" id="reason" rows="4" col="20"
        name="reason" placeholder="填写申请理由"></textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="button" class="btn btn-default" id="appButton">提交申请</button>
      <button type="reset" class="btn btn-danger">重填</button>
    </div>
  </div>
</form>
</div>
</div>
</body>
</html>