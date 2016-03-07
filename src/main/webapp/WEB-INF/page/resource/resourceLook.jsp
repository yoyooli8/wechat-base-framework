<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 头部导航栏 -->
<jsp:include page="../header.jsp"></jsp:include>
<title>资源管理</title>
<style type="text/css">
.dz-res-main{
	margin: 0 200px 0 195px;
}
#dz_tione{
	text-align: center;
	height: 100px;
	line-height: 100px;
	width: 360px;
	background-color: #BBBBBB;
	opacity: 0.5;
}
#dz_tione:HOVER {
	opacity: 0.8;
	cursor: pointer;
}
#dz_timany{
	margin-top: 5px;
	text-align: center;
	height: 100px;
	line-height: 100px;
	width: 360px;
	background-color: #888888;
	opacity: 0.5;
}
#dz_timany:HOVER {
	opacity: 0.8;
	cursor: pointer;
}
#dz_res_ifm{
	position: absolute;
	top: 60px;
	left: 155px;
	border: 2px solid #0099CC;
	box-shadow: 4px 8px 5px #888888;
	display: none;
	z-index: 1000;
}
.dz-one-sty{
	width: 360px;
}
.dz-one-sty div{
	width: 360px;
	line-height: 26px;
	border: 1px solid #DDDDDD;
}
.dz-one-sty img{
	width: 360px;
	height: 200px;
}
.dz_many_sty{
	width: 360px;
}
.dz_many_sty div{
	width: 360px;
	position: absolute;
	background-color: #EEEEEE;
	margin-top: -60px;
	height: 60px;
	line-height: 30px;
	opacity: 0.7;
	color: #000000;
}
.dz_many_sty img{
	width: 360px;
	height: 200px;
}
.dz_many_tty{
	width: 360px;
	height: 100px;
	padding: 10px;
	background-color: #DDDDDD;
	border-bottom: 1px solid #BBBBBB;
}
.dz_many_tty div{
	display: inline-block;
	float: left;
	width: 260px;
	overflow-y: hidden;
	height: 80px;
}
.dz_many_tty img{
	display: inline-block;
	float: right;
	width: 80px;
	height: 80px;
}
.dz-oall:HOVER {
	cursor: pointer;
	opacity: 0.7;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
});
</script>
</head>
<body>
        <div class="dz-head-bg"></div>
        <div class="panel panel-primary dz-main container">
            <div class="panel-heading">
                <h3 class="panel-title dz-title">资源管理</h3>
                <button class="btn pull-right btn-primary"
                        onclick="window.location.href='<%=request.getContextPath()%>/3ti/resource/index'">
                    返回
                </button>
            </div>
            <div class="panel-body">
		<!--头部标题===end===-->
		<form id="dz_form" class="form-horizontal" action="" method="post">
			<div>
			</div>
			<div class="form-group">
				<label for="dz-id-1" class="col-xs-2 control-label">资源名称：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="dz-res_na" name="name" value='${res.name}' disabled>
				</div>
				<div class="col-sm-4">
				</div>
			</div>
			<div class="form-group">
				<label for="dz-id-1" class="col-xs-2 control-label">资源描述：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="description" value='${res.description}' disabled>
				</div>
				<div class="col-sm-4">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 control-label">资源类型：</label>
				<div class="col-sm-6">
					<div class="btn-group" data-toggle="buttons">
						<c:if test="${res.type==1}">
							<button class="btn btn-info btn-sm disabled" style="width:100px;">文本</button>
						</c:if>
						<c:if test="${res.type==2}">
							<button class="btn btn-info btn-sm disabled" style="width:100px;">单图文</button>
						</c:if>
						<c:if test="${res.type==3}">
							<button class="btn btn-info btn-sm disabled" style="width:100px;">多图文</button>
						</c:if>
					</div>
				</div>
				<div class="col-sm-4">
				</div>
			</div>
			<div id="dz_ti_text" class="form-group">
				<c:if test="${res.type==1}">
					<label for="dz-id-2" class="col-xs-2 control-label">文本内容：</label>
					<div class="col-sm-6">
						<textarea id="dz_res_con" class="form-control" rows="12" name="content" disabled>${res.content}</textarea>
					</div>
					<div class="col-sm-4">
					</div>
				</c:if>
			</div>
		</form>
		<div class="dz-res-main">
			<div id="dz_ti_one">
				<c:if test="${res.type==2}">
					<c:forEach var="a" items="${res.article}" varStatus="status">
						<div class="dz-one-sty">
							<span>
								<input type="hidden" class="dz-artId" value="${a.artId}"/>
							</span>
							<div>${a.title}</div>
							<img src="${a.picUrl}">
							<div>${a.description}</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div id="dz_ti_many">
				<c:if test="${res.type==3}">
					<c:forEach var="a" items="${res.article}" varStatus="status">
						<c:if test="${status.index == 0}">
							<div class="dz_many_sty">
								<span>
									<input type="hidden" class="dz-artId" value="${a.artId}"/>
								</span>
								<img src="<%=request.getContextPath()%>${a.picUrl}">
								<div>${a.title}</div>
							</div>
						</c:if>
						<c:if test="${status.index != 0}">
							<div class="dz_many_tty">
								<span>
									<input type="hidden" class="dz-artId" value="${a.artId}"/>
								</span>
								<div>${a.title}</div>
								<img src="<%=request.getContextPath()%>${a.picUrl}">
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
