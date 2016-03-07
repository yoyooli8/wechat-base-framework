<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <!-- 头部导航栏 -->
    <jsp:include page="../header.jsp"></jsp:include>
    <title>中奖详情</title>

</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">中奖详情</h3>
        <button type="button" onclick="location.href='<%=basePath%>log/list'"class="btn btn-primary glyphicon glyphicon-picture pull-right">
            返回
        </button>
    </div>
    <div class="panel-body">
        <div class="bs-example" style="margin-top:50px;" >
            <!-- 商品表单  begin-->
            <form class="form-horizontal"  >
            <div class="form-group">
                <label  class="col-sm-2 control-label">奖品名称
                    :</label>
                <div class="col-sm-4" >
                    <input class="form-control" disabled="disabled" value="${prizeLog.prizeName}"/>
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-2 control-label">奖品等级
                    :</label>
                <div class="col-sm-4">
                    <c:if test="${prizeLog.awaRank==1}">一等奖</c:if>
                    <c:if test="${prizeLog.awaRank==2}">二等奖</c:if>
                    <c:if test="${prizeLog.awaRank==3}">三等奖</c:if>
                </div>
            </div>


            <div class="form-group">
                <label  class="col-sm-2 control-label">用户openId
                    :</label>
                <div class="col-sm-4">
                    <input  class="form-control" disabled="disabled" value="${prizeLog.user_openid}"/>
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">用户名称
                    :</label>
                <div class="col-sm-4">
                    <input class="form-control" disabled="disabled" value="${prizeLog.user_nickname}"/>
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">时间
                    :</label>
                <div class="col-sm-4">
                    <input class="form-control" disabled="disabled" value="${prizeLog.time}"/>
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-2 control-label">状态
                    :</label>
                <div class="col-sm-4">
                    <c:if test="${prizeLog.status==0}">未领取</c:if>
                    <c:if test="${prizeLog.status==1}">已领取</c:if>
                </div>
            </div>
        </form>

        </div>
    </div>
</div>
</body>
</html>
