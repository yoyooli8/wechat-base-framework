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
    <title>关键字管理</title>
    <script type="text/javascript">

        function showRes(){
            $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/resource/show");
            $("#dz_ifm_div").show();
        }

        //添加资源
        document.addRes = function(id, t, n) {
            $("#dz_wk_rid").val(id);
            $("#dz_wk_name").val(n);

            var h = "";
            if(t == 1) {
                h = "文本";
            }else if(t == 2) {
                h = "单图文";
            }else if(t == 3) {
                h = "多图文";
            }
            $("#re_type").html(h);
            $("#re_name").html(n);
            $("#dz_ifm_div").hide();
        };
    </script>

</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">关键字添加</h3>
        <button type="button" onclick="location.href='<%=basePath%>3ti/keyword/index'"class="btn btn-primary glyphicon glyphicon-picture pull-right">
            返回
        </button>
    </div>
    <div class="panel-body">
        <div class="bs-example" style="margin-top:50px;" >
            <!-- 商品表单  begin-->
            <form class="form-horizontal"  id="myform" name="myform"   action="save"
                  method="post" enctype="multipart/form-data">
                <input  type="hidden"   class="form-control" id="id"
                        name="id" value="${keyword.id}" data-container="body"
                        data-placement="right" >
                <input id="dz_wk_rid" type="hidden" name="resId" value='${!empty keyword ? keyword.resId :""}'/>
                <input id="dz_wk_name" type="hidden" name="resName" value='${!empty keyword ? keyword.resName :""}'/>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">关键字
                        :</label>
                    <div class="col-sm-4" id="ddy">
                        <input type="text" data-rule="filter;required;length[0~40];remote[<%=basePath%>/3ti/keyword/checkNameExist]"
                               class="form-control" name="wxKey" data-container="body"
                               data-placement="right"  value="${keyword.wxKey}"/>
                        <span id="name">
                        </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">资源：</label>
                    <div class="col-xs-8">
                        <table class="table table-bordered">
                            <col width="60px;"/>
                            <col width="120px;"/>
                            <col width="60px;"/>
                            <col width="193px;"/>
                            <tr>
                                <td>类型：</td>
                                <td id="re_type">
                                    <c:if test="${res.type==1}">文本</c:if>
                                    <c:if test="${res.type==2}">单图文</c:if>
                                    <c:if test="${res.type==3}">多图文</c:if>
                                </td>
                                <td>名称：</td>
                                <td id="re_name">${res.name}</td>
                                <td>
                                    <button id="dz_add_res" onclick="showRes()" type="button" class="btn btn-primary btn-sm">添加</button></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-4">

                        <input type="submit" class="btn btn-success" id="submitNetWork" value="提交" />

                    </div>
                </div>
                <div class="dz-menu-ifm">
                    <iframe id="menu_iframe" name="menu_data" height="360px" width="800px" frameborder="no"
                            border="0"></iframe>
                </div>

            </form>
        </div>
    </div>
    <div id="dz_ifm_div" style="border: 5px solid #3577B1;position: absolute;top: 120px;right: 300px;display: none;">
        <iframe id="zd_iframe" name="resource_data" height="630px" width="900px" frameborder="no" border="0"></iframe>
    </div>
</div>
</body>
</html>
