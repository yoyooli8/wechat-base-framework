<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<!-- 头部导航栏 -->
<title>适马图片管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/docs.css">
	<script type="text/javascript" src="<%=basePath%>resource/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-transition.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-alert.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-typeahead.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/bootstrap-affix.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteJs.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteHelp.js"></script>
<style>
#imgcol {
	border: none;
	background-color: none;
}

#imgcol:hover {
	cursor: pointer;
	background-color: #DDDDDD;
}

.infoDiv {
	padding: 3px;
	color: #FFFFFF;
	background: #ccc;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dz_the_id").click(function(){parent.document.closeWin();});
		$("img").click(function(){
			parent.document.addImage($(this).attr("id")); 
		});
	});
	
</script>
</head>
<body>
	<div style="width:100%; text-align:right;margin-top: 10px;">
		
	</div>
	<div class="panel-body">
		<!--图片LIST===开始===-->
		<div class="row" style="padding:5px;margin:0px;">
			<c:forEach var="img" items="${imgList}" varStatus="status">
				<div class="col-sm-2 col-md-2" id="imgBox${img.id}">
					<a href="javascript:void(0)" class="thumbnail"> <img
						width="100%" data-src="holder.js/100%x128"
						src="<%=basePath%>${img.tempImg}" id="<%=basePath%>${img.url}"> 
					</a>
				</div>
			</c:forEach>
		</div>
		<!--图片LIST===开始===-->
	</div>
		<footer>
			<div style="position:fixed; background-color:#DDDDDD; width:100%; text-align:center; bottom:0;">
				<button id="dz_the_id" type="button" class="btn btn-info btn-sm" style="width:100px;" onclick="closeWins();">关闭</button>
				<button type="button" class="btn btn-default btn-sm" style="width:100px;" onclick="javascript:window.history.back();">返回</button>
			</div>
		</footer>
</body>
</html>
