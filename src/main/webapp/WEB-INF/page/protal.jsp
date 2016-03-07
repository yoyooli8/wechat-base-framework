<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- 头部导航栏 -->
<title>微信信息管理</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<div class="dz-head-bg"></div>
	<div class="panel panel-primary dz-main container">
	  <div class="panel-heading">
	  	
	    <h3 class="panel-title dz-title">系统公告信息</h3>
	  </div>
	  <div class="panel-body">
	    Panel content
	  </div>
	</div>
</body>
</html>
