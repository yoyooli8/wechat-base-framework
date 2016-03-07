<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap.min.css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/docs.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/glDatePicker.default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/jquery.validator.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap-datetimepicker.min.css">

    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteJs.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteHelp.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/jquery.validator.js"></script>
	<%--<script type="text/javascript" src="<%=basePath%>resource/js/zh_CN.js"></script>--%>
	<script type="text/javascript" src="<%=basePath%>resource/js/zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</head>
<body>
<!--菜单导航===begin===-->
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a  class="navbar-brand" style="color:#FFFFFF;">紫峰</a>
    <%--  <a href="<%=basePath%>/3ti/menu/index" class="navbar-brand" style="color:#FFFFFF;">Sigma</a>  --%>
    </div>
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav" style="font-size:16px;color:#FFFFFF;">
<!--       <li><a href="javascript:void(0);">首页</a></li> -->
		 <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">微信管理 <span class="caret" style="border-top-color:#fff;"></span></a>
              <ul class="dropdown-menu">
                  <li>
                      <a href="<%=request.getContextPath()%>/3ti/resource/index">资源管理</a>
                  </li>
                  <li>
                      <a href="<%=request.getContextPath()%>/3ti/menu/index">菜单管理</a>
                  </li>
                  <li>
                      <a href="<%=request.getContextPath()%>/3ti/keyword/index">关键字管理</a>
                  </li>
                  <li>
                      <a href="<%=request.getContextPath()%>/3ti/resource/image">微信图片</a>
                  </li>
              </ul>
          </li>

          <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">转盘管理 <span class="caret" style="border-top-color:#fff;"></span></a>
              <ul class="dropdown-menu">
                  <li>
                      <a href="<%=request.getContextPath()%>/award/list">奖品管理</a>
                  </li>
                  <li>
                      <a href="<%=request.getContextPath()%>/log/list">中奖查询</a>
                  </li>
              </ul>
          </li>

    </ul>
    <p class="navbar-text navbar-right">欢迎${sessionScope.admin.userName}【<a href="<%=path%>/Admin/toLoginAdmin" class="navbar-link">退出</a>】</p>
    </nav>
  </div>
</header>

