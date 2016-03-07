<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath %>resource/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=basePath %>resource/css/common.css">
<script src="<%=basePath %>resource/js/jquery-1.9.1.js"></script>
<script src="<%=basePath %>resource/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#dz_close").click(function() {
		$(parent.document).find("#dz_ifm_div").hide();
	});
});

//添加资源事件
function addRes(id, t, n) {
	parent.document.addRes(id, t, n);
};
</script>
</head>
<body>
	<!--数据表格===begin===-->
	<table class="table table-bordered">
		<col width="37px;"/>
		<col width="250px;"/>
		<col width="150px;"/>
		<col width="350px;"/>
		<col width=""/>
		<tr>
			<th></th>
			<th>资源名称</th>
			<th>资源类型</th>
			<th>资源描述信息</th>
			<th>操作</th>
		</tr>
		<c:forEach var="re" items="${page.content}" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.index+1}</td>
				<td>${re.name}</td>
				<td>
					<c:if test="${re.type==1}">文本</c:if>
					<c:if test="${re.type==2}">单图文</c:if>
					<c:if test="${re.type==3}">多图文</c:if>
				</td>
				<td>${re.description}</td>
				<td>
					<button class="btn btn-info btn-sm" style="min-width:75px;" onclick="addRes('${re.resourceId}','${re.type}','${re.name}');">添加</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<!--数据表格===end===-->
	<!--表格数据分页===begin===-->
    <ul class="pagination">
        <c:if test="${page.number>0}">
            <li><a href="<%=request.getContextPath()%>/3ti/resource/show?page=${(page.number+1)-1}&sort=${page.sort}">&laquo;</a></li>
        </c:if>

        <c:forEach begin="1" end="${page.totalPages}" var="index">
            <li <c:if test="${index==page.number+1}"> class="active"</c:if>>
                <a href="<%=request.getContextPath()%>/3ti/resource/show?page=${index}&sort=${page.sort}">${index}</a>
            </li>
        </c:forEach>

        <c:if test="${page.number<(page.totalPages-1)}">
            <li><a href="<%=request.getContextPath()%>/3ti/resource/show?page=${(page.number+1)+1}&sort=${page.sort}">&raquo;</a></li>
        </c:if>
        <li><a style="margin-left: 20px;">${page.totalElements}条数据/共${page.totalPages}页</a>
        </li>
    </ul>
	<div style="height:20px;"></div>
	<!--表格数据分页===end===-->
	<div style="position:fixed; z-index:1000; background-color:#DDDDDD; width:100%; text-align:center; bottom:0;">
		<button id="dz_close" class="btn btn-info btn-sm" style="min-width:75px;">关闭</button>
	</div>
</body>
</html>
