<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 头部导航栏 -->
<jsp:include page="../header.jsp"></jsp:include>
<title>图片管理</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#dz-addSmt").click(function() {
		var f = $("#dz_file").val();
		
		if(!f) {
			alert("请选择图片！");return;
		}else {
			var gg = f.substring(f.lastIndexOf("."), f.length);
			if(gg.toUpperCase() != ".JPG") {
				alert("图片格式仅支持JPG！");return;
			}
		}
        $("#name").val(f);
		$("#dz_form").submit();
	});
});

//删除图片
function deleteImg(i) {
	if(confirm("确定删除该图片吗？")) {
		window.location.href = "<%=request.getContextPath()%>/3ti/resource/deleteImage?id=" + i;
	}
};
</script>
<style type="text/css">
#dz_image img{
	width: 175px;
	height: 98px;
}
.dz-image:HOVER {
	cursor: pointer;
}
.row{
	margin-bottom: 5px;
}
</style>
</head>
<body>
        <div class="dz-head-bg"></div>
        <div class="panel panel-primary dz-main container">
            <div class="panel-heading">
                <h3 class="panel-title dz-title">图片管理</h3>
                <div class="dz-btn-group" >
                    第${page}页/共${sum}页（共${count}张）
                    <a class="btn btn-primary btn-sm" href="<%=request.getContextPath()%>/3ti/resource/image?page=${last}" style="min-width:30px;">&laquo;</a>
                    <a class="btn btn-primary btn-sm" href="<%=request.getContextPath()%>/3ti/resource/image?page=${next}" style="min-width:30px;">&raquo;</a>
                </div>
            </div>
           <div class="panel-body">
		<!--头部标题===end===-->

		<form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/3ti/resource/addImage" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-xs-2 control-label">上传图片：</label>
				<div class="col-sm-3">
					<input type="file" id="dz_file" name="imgFile" class="btn btn-primary btn-sm"/>
				</div>
				<div class="col-sm-3">
                    仅支持JPG格式的图片
				</div>
                <input type="hidden" readonly id="name" name="name" class="btn btn-primary btn-sm"/>
				<div class="col-sm-4">
					<button id="dz-addSmt" type="button" class="btn btn-primary btn-sm" style="width:100px;">上传</button>
				</div>

			</div>
		</form>
		<div id="dz_image" style="margin-left: 10px;">
			<div class="row">
                <c:forEach var="i" items="${imgs}" varStatus="status">
                    <div class="col-sm-2 col-md-2" id="imgBox${i.id}">
                        <button type="button"  class="close" onclick="deleteImg('${i.id}')" aria-hidden="true">&times;</button>
                        <a href="javascript:void(0)" class="thumbnail">
                            <p> <small>${i.name}</small> </p>
                            <img width="100%" data-src="holder.js/100%x128"  src="<%=request.getContextPath()%>${i.url}" id="${i.url}">
                        </a>
                    </div>
                    <c:if test="${(status.index+1)%6 == 0}"></div><div class="row"></c:if>
                </c:forEach>
			</div>
		</div>
	</div>
            </div>
</body>
</html>
