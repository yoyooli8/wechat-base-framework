<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
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
	<script type="text/javascript" src="<%=basePath%>resource/js/glDatePicker.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteJs.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/utilJs/deleteHelp.js"></script>
<!-- 头部导航栏 -->
<title>适马图片管理</title>
<style>
#imgcol{
	border:none;
	background-color:none;
}
#imgcol:hover{
	background-color:#DDDDDD;
	border-color:#4bf21a;
}
#typeimg:hover{
	cursor:pointer;
}
.infoDiv{
	padding:3px;
	color:#FFFFFF;
	background:#ccc;
}
</style>
<script>
$(document).ready(function() {
	$("#dz_the_id").click(function(){
			parent.document.closeWin();
		}
	);
});
function cancel(){
		$('#addTypeBtn').show();
		$('#myform').hide();
		$("#typeId").val("");
		$("#typeName").val("");
		$("#typeDecription").val("");
}
	function editImageType(id){
		$.ajax({
			url : "process",
			data : "id="+id,
			type : "post",
			success:function(data){
				var result = eval("["+data+"]");
				$("#typeId").val(result[0].id);
				$("#typeName").val(result[0].typeName);
				$("#typeDecription").val(result[0].typeDecription);
				$('#myform').show();
				$('#addTypeBtn').hide();
			}
		});
	}
	
	//删除图集
	function deleteType(id){
		$.ajax({
			url:"delete",
			data: "id="+id,
			success:function(data){
				data==1?$("#typeBox"+id).fadeOut("slow",function(){
					window.location.reload();					
				}):alert("删除失败");
			}
		});
	}
</script>
</head>
<body>

		<div class="panel-body">
		<!--表格数据分页===begin===-->
		<ul class="pagination">
			<c:if test="${page.number>0}">
				<li><a href="imagetypelist?page=${(page.number+1)-1}&sort=${page.sort}">&laquo;</a></li>
			</c:if>
			<c:forEach begin="1" end="${page.totalPages}" var="index">
				<li>
					<a href="imagetypelist?page=${index}&sort=${page.sort}">${index}</a>
				</li>
			</c:forEach>
			<c:if test="${page.number<(page.totalPages-1)}">
				<li><a href="imagetypelist?page=${(page.number+1)+1}&sort=${page.sort}">&raquo;</a></li>
			</c:if>
			<li><a style="margin-left:20px;">${page.totalElements}条数据/共${page.totalPages}页</a>
			</li>
		</ul>
		<!--表格数据分页===end===-->
		<!-- 图集分类开始 -->
				<div class="row" style="padding:5px;margin:0px;">
					<div class="row" style="padding:5px;margin:0px;">
					<c:forEach var="imgtype" items="${page.content}" varStatus="status">
						<div class="col-sm-2 col-md-2" id="typeBox${imgtype.id}">
						    <div id="imgcol"  class="thumbnail">
						      <img onclick="location.href='<%=basePath%>/article/imagelist?typeId=${imgtype.id}';" width="100%"  data-src="holder.js/100%x128" id="typeimg" src="<%=basePath%>${imgtype.typeUrl!=null?imgtype.typeUrl:'resource/image/forder.jpg'}">
						        <div class="caption" style="margin:0px;padding:0px;">
						          <h5 style="text-align:center;">${imgtype.typeName}(${fn:length(imgtype.list)})</h5>
						          <p class="infoDiv" >${imgtype.typeDecription}</p>
						        </div>
						    </div>
						  </div>
				  </c:forEach>
				</div>
				</div>
				<!-- 图集分类开始 -->
		</div>
		<footer>
			<div style="position:fixed; background-color:#DDDDDD; width:100%; text-align:center; bottom:0;">
				<button id="dz_the_id" type="button" class="btn btn-info btn-sm" style="width:100px;" onclick="closeWins();">关闭</button>
			</div>
		</footer>
</body>
</html>
