<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/jquery.validator.css">
    <script src="<%=request.getContextPath()%>/resource/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery.validator.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            width: 760px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            //两种种类型切换事件
            $("#wnn_url").click(function() {
                $("#dz_url").show();
                $("#dz_res").hide();
            });
            $("#wnn_res").click(function() {
                $("#dz_res").show();
                $("#dz_url").hide();
            });

            $("#dz_add_res").click(function() {
                parent.document.showRes();
            });

            //提交数据
            $("#dz-addSmt").click(function() {
                var n = $("#dz_me_nm").val();
                var t = $("input[type='radio'][name='type']:checked").val();

                if(!n) {
                    alert("请输入菜单名称！");return;
                }else if(n.length > 20) {
                    alert("菜单名称不能超过20个字符！");return;
                }

                if(!t) {
                    alert("请选择菜单类型！");return;
                }


                if("view" == t) {
                    var url = $("#dz_me_url").val();
                    if(!url) {
                        alert("请输入URL地址！");return;
                    }else if(url.length > 250){
                        alert("URL地址不能超过250个字符！");return;
                    }
                }else if("click" == t) {
                    var rd = $("#dz_wk_rid").val();
                    if(rd==0 || !rd) {
                        alert("请添加事件响应资源！");return;
                    }
                }

                $("#dz_form").submit();
            });

            //修改时，类型按钮事件
            var type = "${m.type}";
            if("view" == type) {
                $("#wnn_url").click();
            }else if("click" == type) {
                $("#wnn_res").click();
            }
        });

        //添加资源
        document.addRes = function(id, t, n) {
            $("#dz_wk_rid").val(id);

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
        };

    </script>
</head>
<body>
<!--头部标题===begin===-->
<div class="dz-manager">
    <div class="dz-title">子菜单维护</div>
</div>
<c:if test="${flag != null}">
无法创建子菜单，主菜单最多拥有5个子菜单。
<div style="display: none;">
    </c:if>
    <form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/menu/save" method="post">
        <div class="form-group">
            <label class="col-xs-2 control-label">名称：</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="dz_me_nm" name="menuName"
                       data-rule="filter;required;length[1~20];remote[<%=basePath %>3ti/menu/checkNameExist]"  value="${menu.menuName}">
                <!-- 隐藏属性 -->
                <input id="dz_wk_pid" type="hidden" name="parId" value='${!empty menu ?  menu.parId : ""}'/>
                <input id="dz_wk_mid" type="hidden" name="meunId" value='${!empty menu ?  menu.meunId :""}'/>
                <input id="dz_wk_flag" type="hidden" name="flag" value='2'/>
                <input id="dz_wk_rid" type="hidden" name="resourceId" value='${!empty menu ? menu.resourceId :""}'/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">类型：</label>
            <div class="col-xs-8">
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-info" id="wnn_url">
                        <input type="radio" name="type" value="view">链接
                    </label>
                    <label class="btn btn-info" id="wnn_res">
                        <input type="radio" name="type" value="click">资源
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group" id="dz_url" style="display:none;">
            <label class="col-xs-2 control-label">URL：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="dz_me_url" name="url" value='${menu.url}'>
            </div>
        </div>
        <div id="dz_res" style="display:none;">
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
                            <td><button id="dz_add_res" type="button" class="btn btn-primary btn-sm">添加</button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <hr/>
        <div style="padding-left:134px;">
            <button id="dz-addSmt" type="button" class="btn btn-primary btn-sm" style="width:100px;">提交</button>
        </div>
    </form>
</div>

</body>

</html>
