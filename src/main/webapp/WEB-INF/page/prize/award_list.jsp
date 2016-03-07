<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xa3ti.business.entity.Award" %>
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
<jsp:include page="../header.jsp"></jsp:include>
<title>奖品列表</title>
</head>
<body>
	<div class="dz-head-bg"></div>
	<div class="panel panel-primary dz-main container">
		<div class="panel-heading">
			<h3 class="panel-title dz-title">奖品列表</h3>
			<button class="btn btn-primary pull-right" onclick="window.location.href='<%=basePath%>award/add'">新增奖品</button>
		</div>
		<div class="panel-body" >

			<div style="height: 20px;"></div>

			<table class="table table-bordered" style="text-align: center;">
				<col width="10%"/>
				<col />
				<col width="15%"/>
				<col width=""/>
				<col width=""/>
				<col width="10%"/>
				<col width="13%"/>
				<tr>
					<th>编号</th>
					<th>名称</th>
					<th>描述</th>
					<th>总数</th>
					<th>奖项</th>
					<th>中奖概率</th>
					<th>操作</th>
				</tr>
				<!-- 礼品列表展示开始 -->
			<c:forEach var="gift" items="${page.content}" varStatus="in">
				<tr id="gift${gift.awaId}">
					<td>${gift.awaId}</td>
					<td>${gift.awaName}</td>
					<td>${gift.info}</td>
					<td>${gift.awaNum}</td>
					<td>
                        <c:if test="${gift.awaRank==1}">一等奖</c:if>
                        <c:if test="${gift.awaRank==2}">二等奖</c:if>
                        <c:if test="${gift.awaRank==3}">三等奖</c:if>
                    </td>
					<td>${gift.probability}</td>
					<td style="overflow: visible;">
						<div class="btn-group">
							<button class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown">操作</button>
							<ul class="dropdown-menu">
								<li><a href="<%=basePath%>award/edit?id=${gift.awaId}">修改</a></li>
							</ul>
						</div>
					</td>		
				</tr>
			</c:forEach>
				<!-- 用戶列表展示結束 -->
			</table>

			<%--<!--表格数据分页===开始===-->--%>
			<%--<ul class="pagination">--%>
			 <%--<input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber }"/> --%>
				<%--<c:if test="${page.number>0}">--%>
					<%--<li><a href="<%=basePath%>gift/list?pageNumber=${(page.number+1)-1}&sort=${page.sort}">&laquo;</a></li>--%>
				<%--</c:if>--%>
				<%----%>
				<%--<c:forEach begin="1" end="${page.totalPages}" var="index">--%>
					<%--<c:if test="${pageNumber==index }">              	--%>
                   	 <%--<li class="active"><a href="<%=basePath%>gift/list?pageNumber=${index}&sort=${page.sort}">${index}</a></li>--%>
                	<%--</c:if>--%>
                	<%--<c:if test="${pageNumber!=index }">      --%>
                	<%--<li><a href="<%=basePath%>gift/list?pageNumber=${index}&sort=${page.sort}">${index}</a>--%>
                    <%--</li>--%>
                    <%--</c:if>--%>
				<%--</c:forEach>--%>
				<%----%>
				<%--<c:if test="${page.number<(page.totalPages-1)}">--%>
					<%--<li><a href="<%=basePath%>gift/list?pageNumber=${(page.number+1)+1}&sort=${page.sort}">&raquo;</a></li>--%>
				<%--</c:if>--%>
				<%--<li><a style="margin-left: 20px;">${page.totalElements}条数据/共${page.totalPages}页</a>--%>
				<%--</li>--%>
			<%--</ul>--%>
			<!--表格数据分页===end===-->
		</div>

	</div>
</body>
</html>
