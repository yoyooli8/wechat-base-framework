<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>资源管理</title>
</head>
<body>
<!-- 头部导航栏 -->
<jsp:include page="../header.jsp"></jsp:include>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">资源管理</h3>
        <button class="btn pull-right btn-primary"
                onclick="window.location.href='<%=request.getContextPath()%>/3ti/resource/create'">
            新建资源
        </button>
    </div>
    <div class="panel-body">
        <!--数据表格===begin===-->
        <table class="table table-bordered">
            <col width="50px;"/>
            <col width="37px;"/>
            <col width="300px;"/>
            <col width="200px;"/>
            <col width="400px;"/>
            <col width="130px;"/>
            <tr>
                <th>关注消息</th>
                <th></th>
                <th>资源名称</th>
                <th>资源类型</th>
                <th>资源描述信息</th>
                <th>操作</th>
            </tr>
            <c:forEach var="r" items="${page.content}" varStatus="status">
                <tr>
                    <td style="text-align:center;">
                        <input type="radio" class="item-check-box"
                               onchange="updateIndex(${r.resourceId})"
                            ${(r.resourceId==index)? "checked='checked'":""}
                               name="radioBox" value="${r.resourceId}" />
                    </td>
                    <td style="text-align:center;">${r.resourceId}</td>
                    <td>${r.name}</td>
                    <td>
                        <c:if test="${r.type == 1}">文本</c:if>
                        <c:if test="${r.type == 2}">单图文</c:if>
                        <c:if test="${r.type == 3}">多图文</c:if>
                    </td>
                    <td>${r.description}</td>
                    <td style="overflow:visible; padding:3px 0 0 15px;">
                        <div class="btn-group">
                            <button class="btn btn-info btn-sm" style="min-width:75px;">操作</button>
                            <button class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown"><span
                                    class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="<%=request.getContextPath()%>/3ti/resource/process?resourceId=${r.resourceId}&look=look">查看</a>
                                </li>
                                <li>
                                    <a href="<%=request.getContextPath()%>/3ti/resource/process?resourceId=${r.resourceId}">修改</a>
                                </li>
                                <li>
                                	<a href="#" onclick="javascript:if(confirm('确定要删除资源数据吗？'))window.location.href='<%=request.getContextPath()%>/3ti/resource/delete?resourceId=${r.resourceId}';">删除</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <ul class="pagination">
            <c:if test="${page.number>0}">
                <li><a href="<%=request.getContextPath()%>/3ti/resource/index?pageNumber=${(page.number+1)-1}&sort=${page.sort}">&laquo;</a></li>
            </c:if>

            <c:forEach begin="1" end="${page.totalPages}" var="index">
                <li <c:if test="${index==page.number+1}"> class="active"</c:if>>
                    <a href="<%=request.getContextPath()%>/3ti/resource/index?pageNumber=${index}&sort=${page.sort}">${index}</a>
                </li>
            </c:forEach>

            <c:if test="${page.number<(page.totalPages-1)}">
                <li><a href="<%=request.getContextPath()%>/3ti/resource/index?pageNumber=${(page.number+1)+1}&sort=${page.sort}">&raquo;</a></li>
            </c:if>
            <li><a style="margin-left: 20px;">${page.totalElements}条数据/共${page.totalPages}页</a>
            </li>
        </ul>
        <!--表格数据分页===end===-->
    </div>
</body>
<script type="text/javascript">
    function updateIndex(id) {
        $.ajax({
            url: "<%=path%>/3ti/resource/updateIndex?index=" + id,
            type: "get",
            success: function (data) {
               if(data){
                   alert("修改关注回复消息成功");
                   window.location.reload();
               }else
               {
                   alert("修改失败");
               }
            }
        });
    }


</script>

</html>
