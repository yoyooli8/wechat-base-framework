<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="duanqixin"/>
<link rel="shortcut icon" type="image/x-png" href="<%=path%>/resource/image/head.png" media="screen"/>
<link rel="stylesheet" href="<%=path%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/resource/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=path%>/resource/css/common.css">
<script src="<%=path%>/resource/js/jquery-1.9.1.js"></script>
<script src="<%=path%>/resource/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/jquery.validator.css">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery.validator.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/zh_CN_sigma.js"></script>
<style type="text/css">
.dz-login{
	height: 400px;
	margin-top: 50px;
	padding-top: 50px;
	border-left: 1px solid #DDDDDD;
}
.dz-login-btn{
	margin-top: 50px;
	padding-left: 180px;
}
</style>
</head>
<body>
	<div class="dz-head-bg">
	</div>
	<div class="container dz-main">
	
		<div class="row">
			<div class="col-xs-6">
				<img src="<%=path%>/resource/image/login_img.png" style="margin:30px 0 0 55px;">
			</div>
			<div class="col-xs-6 dz-login">
			<span id="sp" style="font-style:italic;color:red">${errorMsg}</span>
				<form id="dz_form" class="form-horizontal" action="<%=path%>/Admin/loginAdmin" method="post">
					<div class="form-group">
						<label for="dz-id-1" class="col-xs-2 control-label">UserName</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="userName" data-rule="required;" name="userName" value="">
						</div>
						<div class="col-sm-4">
							<font id="dz_um_alt" color="red"></font>
						</div>
					</div>
					<div class="form-group">
						<label for="dz-id-2" class="col-xs-2 control-label">Password</label>
						<div class="col-sm-6">
							<input  class="form-control" id="pwd"  type="password" name="pwd" data-rule="required;" value="">
						</div>
						<div class="col-sm-4">
							<font id="dz_um_alt" color="red"></font>
						</div>
					</div>
					<div class="dz-login-btn">
						<button id="dz_smb" type="submit" class="btn btn-info btn-sm" style="width: 120px;">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#userName").blur(function() {
			$("#sp").html("");
		});
		$("#pwd").blur(function() {
			$("#sp").html("");
		});
	</script>
</body>
</html>
