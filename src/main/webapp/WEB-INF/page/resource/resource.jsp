<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- 头部导航栏 -->
    <jsp:include page="../header.jsp"></jsp:include>
    <title>资源管理</title>
    <style type="text/css">
        .dz-res-main {
            margin: 0 200px 0 195px;
        }

        #dz_tione {
            text-align: center;
            height: 100px;
            line-height: 100px;
            width: 360px;
            background-color: #BBBBBB;
            opacity: 0.5;
        }

        #dz_tione:HOVER {
            opacity: 0.8;
            cursor: pointer;
        }

        #dz_timany {
            margin-top: 5px;
            text-align: center;
            height: 100px;
            line-height: 100px;
            width: 360px;
            background-color: #888888;
            opacity: 0.5;
        }

        #dz_timany:HOVER {
            opacity: 0.8;
            cursor: pointer;
        }

        #dz_res_ifm {
            position: absolute;
            top: 60px;
            left: 155px;
            border: 2px solid #0099CC;
            box-shadow: 4px 8px 5px #888888;
            display: none;
            z-index: 1000;
        }

        .dz-one-sty div {
            width: 360px;
            line-height: 26px;
            border: 1px solid #DDDDDD;
        }

        .dz-one-sty img {
            width: 360px;
            height: 200px;
        }

        .dz_many_sty div {
            position: absolute;
            background-color: #EEEEEE;
            margin-top: -60px;
            width: 360px;
            height: 60px;
            line-height: 30px;
            opacity: 0.7;
            color: #000000;
        }

        .dz_many_sty img {
            width: 360px;
            height: 200px;
        }

        .dz_many_tty {
            width: 360px;
            height: 100px;
            padding: 10px;
            background-color: #DDDDDD;
            border-bottom: 1px solid #BBBBBB;
        }

        .dz_many_tty div {
            display: inline-block;
            float: left;
            width: 260px;
            overflow-y: hidden;
            height: 80px;
        }

        .dz_many_tty img {
            display: inline-block;
            float: right;
            width: 80px;
            height: 80px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            //提交按钮
            $("#dz_smt_bc").click(function () {
                if ($(".dz-res-main").find("img").length == 0) {
                    alert("请添加合法的图文资源！");
                    return;
                }
                document.location.href = "<%=request.getContextPath()%>/3ti/resource/index";
            });

            //资源类型切换事件
            $("#wnn_text").click(function () {
                $("#dz_ti_text").show();
                $("#dz_smt_res").html("保存");
            });
            $("#wnn_only_tw").click(function () {
                $("#dz_ti_text").hide();
                $("#dz_smt_res").html("下一步");
            });
            $("#wnn_many_tw").click(function () {
                $("#dz_ti_text").hide();
                $("#dz_smt_res").html("下一步");
            });

            var resId = "${res.resourceId}";
            //添加单图文页面
            $("#dz_tione").click(function () {
                $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/article/process?resourceId=" + resId);
                $("#dz_res_ifm").show();
            });

            //添加图文页面
            $("#dz_timany").click(function () {
                $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/article/process?pro=many&resourceId=" + resId);
                $("#dz_res_ifm").show();
            });

            //提交表单数据
            $("#dz_smt_res").click(function () {
                var t = $("input[type='radio'][name='type']:checked").val();

                var rn = $("#dz-res_na").val();
                //资源名称
                if (!rn) {
                    alert("请输入合法的资源名称！");
                    return;
                } else if (rn.trim() == "" || rn.length > 200) {
                    alert("资源名称不能为空字符并且不能超过200个字符！");
                    return;
                }

                //资源描述
                var rd = $("#dz_res_dc").val();
                if (rd && rd.length > 500) {
                    alert("资源描述不能超过500个字符！");
                    return;
                }

                //类型
                if (!t) {
                    alert("请选择资源类型！");
                    return;
                }

                if (t == 1) {
                    var rc = $("#dz_res_con").val();
                    if (!rc) {
                        alert("请输入合法的文本内容！");
                        return;
                    } else if (rc.length > 3000) {
                        alert("文本内容不能超过3000个字符！");
                        return;
                    }
                }

                $("#dz_form").submit();
            });
        });

        //关闭图文窗口
        document.closeWin = function () {
            $("#dz_res_ifm").hide();
        };

        //添加图文数据
        document.addITN = function (id, title, desc, img) {
            var t = $("#dz_res_type").val();
            var u = "<%=request.getContextPath()%>";

            if (t == 2) {//单图文
                var ht = "<div class='dz-one-sty'><div>" + title + "</div><img src='" + u+img + "'><div>" + desc + "</div></div>";
                $("#dz_ti_one").html(ht);
            } else if (t == 3) {//多图文
                var vs = $("#dz_res_arts").val();

                var ht = "";
                if (vs) {//第二条及其以上的数据
                    ht = "<div class='dz_many_tty'><div>" + title + "</div><img src='" +u+ img + "'></div>";
                } else {//第一条数据
                    ht = "<div class='dz_many_sty'><img src='" + u+img + "'><div>" + title + "</div></div>";
                    $("#dz_res_arts").val(id);
                }

                $("#dz_timany").before(ht);
            }
        };
    </script>
</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">新建资源</h3>
        <button class="btn pull-right btn-primary"
                onclick="window.location.href='<%=request.getContextPath()%>/3ti/resource/index'">
            返回
        </button>
    </div>
    <div class="panel-body">
        <!--头部标题===end===-->
        <form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/3ti/resource/save"
              method="post">
            <div>
                <input id="dz_res_type" type="hidden" value='${res.type}'/>
                <input id="dz_res_arts" type="hidden"/>
            </div>
            <div class="form-group">
                <label for="dz-id-1" class="col-xs-2 control-label">资源名称：</label>

                <div class="col-sm-6">
                    <input type="text" class="form-control" id="dz-res_na" name="name" value='${res.name}'
                           <c:if test="${res!=null}">disabled</c:if>/>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div class="form-group">
                <label for="dz-id-1" class="col-xs-2 control-label">资源描述：</label>

                <div class="col-sm-6">
                    <input type="text" class="form-control" id="dz_res_dc" name="description" value='${res.description}'
                           <c:if test="${res!=null}">disabled</c:if>/>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label">资源类型：</label>

                <div class="col-sm-6">
                    <c:if test="${res.type==1}">
                        <button class="btn btn-info btn-sm disabled" style="width:100px;">文本</button>
                    </c:if>
                    <c:if test="${res.type==2}">
                        <button class="btn btn-info btn-sm disabled" style="width:100px;">单图文</button>
                    </c:if>
                    <c:if test="${res.type==3}">
                        <button class="btn btn-info btn-sm disabled" style="width:100px;">多图文</button>
                    </c:if>
                    <c:if test="${res==null}">
                        <label id="wnn_text">
                            <input type="radio" name="type" value="1">文本
                        </label>
                        <label id="wnn_only_tw">
                            <input type="radio" name="type" value="2">单图文
                        </label>
                        <label id="wnn_many_tw">
                            <input type="radio" name="type" value="3">多图文
                        </label>
                    </c:if>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div id="dz_ti_text" class="form-group" style="display:none;">
                <label for="dz-id-2" class="col-xs-2 control-label">文本内容：</label>

                <div class="col-sm-6">
                    <textarea id="dz_res_con" class="form-control" rows="12" name="content"></textarea>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
        </form>
        <div class="dz-res-main">
            <c:if test="${res.type==2}">
                <div id="dz_ti_one">
                    <div id="dz_tione">
                        <span class="glyphicon glyphicon-plus"></span>单图文
                    </div>
                </div>
            </c:if>
            <c:if test="${res.type==3}">
                <div id="dz_ti_many">
                    <div id="dz_timany">
                        <span class="glyphicon glyphicon-plus"></span>图文
                    </div>
                </div>
            </c:if>
        </div>
        <div class="dz-addBtn">
            <c:if test="${res==null}">
                <button id="dz_smt_res" type="button" class="btn btn-primary btn-sm" style="width:100px;">保存</button>
            </c:if>
            <c:if test="${res!=null}">
                <button id="dz_smt_bc" type="button" class="btn btn-primary btn-sm" style="width:100px;">提交</button>
            </c:if>
        </div>
        <div id="dz_res_ifm">
            <iframe id="zd_iframe" name="resource_data" height="600px" width="1130px" frameborder="no"
                    border="0"></iframe>
        </div>
    </div>
    </div>
</body>
</html>
