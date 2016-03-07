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
    <title>奖品录入</title>

</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">奖品录入</h3>
        <button type="button" onclick="location.href='<%=basePath%>award/list'"class="btn btn-primary glyphicon glyphicon-picture pull-right">
            返回
        </button>
    </div>
    <div class="panel-body">
        <div class="bs-example" style="margin-top:50px;" >
            <!-- 商品表单  begin-->
            <form class="form-horizontal"  id="myform" name="myform"   action="<%=basePath%>award/save"
                  method="post" enctype="multipart/form-data">
                <input  type="hidden"
                        name="awaId" value="${award.awaId}"
                        >
                <div class="form-group">
                    <label  class="col-sm-2 control-label">名称
                        :</label>
                    <div class="col-sm-4" id="ddy">
                        <input type="text" data-rule="filter;required;length[0~40];"
                               class="form-control" name="awaName" data-container="body"
                               data-placement="right"  value="${award.awaName}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">描述
                        :</label>
                    <div class="col-sm-4" >
                        <input type="text" data-rule="filter;required;length[0~40];"
                               class="form-control" name="info" data-container="body"
                               data-placement="right"  value="${award.info}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">总数
                        :</label>
                    <div class="col-sm-4" >
                        <input type="text" data-rule="filter;required;length[0~40];range[0~]"
                               class="form-control" name="awaNum" data-container="body"
                               data-placement="right"  value="${award.awaNum}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">奖项
                        :</label>
                    <div class="col-sm-4" >
                        <%--<input type="number" data-rule="filter;required;length[0~40];"--%>
                               <%--class="form-control" name="awaRank" data-container="body"--%>
                               <%--data-placement="right"  value="${award.awaRank}"/>--%>

                        <select  class="input-xxlarge" onclick="" name="awaRank" >
                            <option value="1" <c:if test="${award.awaRank==1}">selected="selected"</c:if>
                                    >一等奖
                            </option>
                            <option value="2" <c:if test="${award.awaRank==2}">selected="selected"</c:if> >二等奖</option>
                            <option value="3" <c:if test="${award.awaRank==3}">selected="selected"</c:if>>三等奖</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">中奖概率
                        :</label>
                    <div class="col-sm-4" >
                        <input type="text" data-rule="filter;required;length[0~40];range[0~1]"
                               class="form-control" name="probability" data-container="body"
                               placeholder="小数"
                               data-placement="right"  value="${award.probability}"/>
                    </div>
                </div>




                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-4">
                        <input type="submit" class="btn btn-success" value="提交" />
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
