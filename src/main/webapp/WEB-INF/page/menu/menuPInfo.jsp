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
            //三种类型切换事件
            $("#wnn_url").click(function() {
                $("#dz_url").show();
                $("#dz_res").hide();
            });
            $("#wnn_res").click(function() {
                $("#dz_res").show();
                $("#dz_url").hide();
            });
            $("#wnn_menu").click(function() {
                $("#dz_res").hide();
                $("#dz_url").hide();
            });

            $("#dz_add_res").click(function() {
                parent.document.showRes();
            });

            //提交数据
            $("#dz-addSmt").click(function() {
                var n = $("#dz_me_nm").val();
                //var t = $("input[type='radio'][name='type']:checked").val();
                if(!n) {
                    alert("请输入菜单名称！");return;
                }else if(n.length > 20) {
                    alert("菜单名称不能超过20个字符！");return;
                }

//		if(!t) {
//			alert("请选择菜单类型！");return;
//		}

//		if("menu" == t) {
//		}else if("view" == t) {
//			var url = $("#dz_me_url").val();
//			if(!url) {
//				alert("请输入URL地址！");return;
//			}else if(url.length > 150){
//				alert("URL地址不能超过150个字符！");return;
//			}
//		}else if("click" == t) {
//			if(!$("#dz_wk_rid").val()) {
//				alert("请添加事件响应资源！");return;
//			}
//		}
                $("#dz_form").submit();
            });

            //修改时，类型按钮事件
            var type = "${menu.type}";
            if("menu" == type) {
                $("#wnn_menu").click();
            }else if("view" == type) {
                $("#wnn_url").click();
            }else if("click" == type) {
                $("#wnn_res").click();
            }
        });

        //添加资源
        document.addRes = function(id, type, name) {
            $("#dz_wk_rid").val(id);

            var str = "";
            if(type == 1) {
                str = "文本";
            }else if(type == 2) {
                str = "单图文";
            }else if(type == 3) {
                str = "多图文";
            }
            $("#re_type").html(str);
            $("#re_name").html(name);
        };
    </script>
</head>
<body>
<!--头部标题===begin===-->
<div class="dz-manager">
    <div class="dz-title">主菜单维护</div>
</div>
<c:if test="${flag != null}">
    无法创建主菜单，主菜单最多创建3个。
</c:if>
<c:if test="${flag == null}">
    <form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/menu/save" method="post">
        <div class="form-group">
            <label class="col-xs-2 control-label">名称：</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="dz_me_nm" name="menuName"  data-rule="filter;required;length[1~20];remote[<%=basePath %>3ti/menu/checkNameExist]"   value='${menu.menuName}'>
                <!-- 隐藏属性 -->
                <input type="hidden" name="flag" value="1"/>
                <c:if test="${!empty menu.meunId}">
                    <input type="hidden" name="meunId" value="${menu.meunId}"/>
                </c:if>
                <input type="hidden" name="type" value="menu">


            </div>
        </div>
        <hr/>
        <div style="padding-left:134px;">
            <button id="dz-addSmt" type="button"
                    class="btn btn-primary btn-sm" style="width:100px;">提交</button>
        </div>
    </form>
</c:if>
</body>
</html>
