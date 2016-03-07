<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css">
<script src="<%=request.getContextPath()%>/resource/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("img").click(function() {
		var url = $(this).attr("id");
		parent.document.addImage(url);
	});
});

//关闭窗口
function closeWin() {
	parent.document.closeWin();
}
</script>
<style type="text/css">
body{
	width: 850px;
	background-color: #EEEEEE;
}
#dz_image{
	padding-left: 15px;
}
img{
	width: 200px;
	height: 80px;
}
img:HOVER {
	cursor: pointer;
}
.row{
	margin-top: 5px;
}
</style>
</head>
<body>

	<!--头部标题===begin===-->
	<div class="dz-manager">
		<div class="panel-title dz-title">图片管理</div>
		<div class="dz-btn-group">
			第${page}页/共${sum}页（共${count}张）
			<a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/3ti/resource/imageList?page=${last}" style="min-width:30px;">&laquo;</a>
			<a class="btn btn-info btn-sm" href="<%=request.getContextPath()%>/3ti/resource/imageList?page=${next}" style="min-width:30px;">&raquo;</a>
		</div>
	</div>
	<!--头部标题===end===-->
    <div class="panel-body">
        <!--图片LIST===开始===-->
        <div class="row" style="padding:5px;margin:0px;">
           <c:forEach var="i" items="${imgs}" varStatus="status">
                <div class="col-xs-3">
                    <a href="javascript:void(0)" class="thumbnail">
                        <p> <small>${i.name}</small> </p>
                        <img src="<%=request.getContextPath()%>${i.url}" id="${i.url}"/>
                    </a>
                </div>
            <c:if test="${(status.index+1)%4 == 0}"></div><div class="row"></c:if>
             </c:forEach>
        </div>
        <!--图片LIST===开始===-->
    </div>
	<div style="position:fixed; background-color:#DDDDDD; width:100%; text-align:center; bottom:0;">
		<button type="button" class="btn btn-info btn-sm" style="width:100px;" onclick="closeWin();">关闭</button>
	</div>
</body>
</html>
