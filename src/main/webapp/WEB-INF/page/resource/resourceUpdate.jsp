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

    .dz-one-sty {
        width: 360px;
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

    .dz_many_sty {
        width: 360px;
    }

    .dz_many_sty div {
        width: 360px;
        position: absolute;
        background-color: #EEEEEE;
        margin-top: -60px;
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

    .dz-oall:HOVER {
        cursor: pointer;
        opacity: 0.7;
    }

    .dz-trash {
        width: 40px;
        height: 90px;
        position: absolute;
        margin-left: 400px;
        line-height: 90px;
        text-align: center;
        margin-top: -95px;
        background-color: #DDDDDD;
        opacity: 0.4;
        cursor: pointer;
    }

    .dz-trash:HOVER {
        opacity: 0.8;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        //添加图文页面
        $("#dz_timany").click(function () {
            var resId = $("#dz_res_rid").val();
            $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/article/process?resourceId=" + resId);
            $("#dz_res_ifm").show();
        });

        //点击图文修改事件
        $(".dz-oall").click(function () {
            var id = $(this).attr("id");

            $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/article/process?artId=" + id);
            $("#dz_res_ifm").show();
        });

        //删除事件
        $(".dz-trash").click(function () {
            if (confirm("确定删除该图文吗？")) {
                var artId = $(this).attr("id");
                var resId = $("#dz_res_rid").val();
                window.location.href = "<%=request.getContextPath()%>/3ti/article/delete?artId=" + artId + "&resId=" + resId;
            }
        });

        //提交表单数据
        $("#dz-addRes").click(function () {
            var t = $("#dz_res_type").val();

            //资源名称
            var rn = $("#dz-res_na").val();
            if (!rn) {
                alert("请输入合法的资源名称！");
                return;
            } else if (rn.length > 100) {
                alert("资源名称不能超过100个字符！");
                return;
            }

            //资源描述
            var rd = $("#dz_res_dc").val();
            if (rd && rd.length > 200) {
                alert("资源描述不能超过200个字符！");
                return;
            }

            //类型
            if (!t) {
                alert("资源类型非法！");
                return;
            }

            if (t == 1) {
                var rc = $("#dz_res_con").val();
                if (!rc) {
                    alert("请输入合法的文本内容！");
                    return;
                } else if (rc.length > 1000) {
                    alert("文本内容不能超过1000个字符！");
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

        if (t == 3) {//多图文
            //第二条及其以上的数据
            var ht = "<div class='dz_many_tty' id='" + id + "'><div>" + title + "</div><img src='" + img + "'></div>";
            $("#dz_timany").before(ht);
        }
    };

    //修改图文数据
    document.updateITN = function (id, title, desc, img) {
        var u = "<%=request.getContextPath()%>";
        var o = $("#" + id + "");

        o.find("#tit").html(title);
        o.find("img").attr("src", u + img);

        var d = o.find("#des");
        if (d) {
            o.find("#des").html(desc);
        }
    };
</script>
</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <h3 class="panel-title dz-title">资源管理</h3>
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
                <input id="dz_res_arts" type="hidden" name="artIds" value=''/>
                <input id="dz_res_rid" type="hidden" name="resourceId" value='${res.resourceId}'/>
                <input id="dz_res_type" type="hidden" name="type" value='${res.type}'/>
            </div>
            <div class="form-group">
                <label for="dz-id-1" class="col-xs-2 control-label">资源名称：</label>

                <div class="col-sm-6">
                    <input type="text" class="form-control" id="dz-res_na" name="name" value='${res.name}'>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div class="form-group">
                <label for="dz-id-1" class="col-xs-2 control-label">资源描述：</label>

                <div class="col-sm-6">
                    <input type="text" class="form-control" id="dz_res_dc" name="description"
                           value='${res.description}'>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 control-label">资源类型：</label>

                <div class="col-sm-6">
                    <div class="btn-group" data-toggle="buttons">
                        <c:if test="${res.type==1}">
                            <button class="btn btn-info btn-sm disabled" style="width:100px;">文本</button>
                        </c:if>
                        <c:if test="${res.type==2}">
                            <button class="btn btn-info btn-sm disabled" style="width:100px;">单图文</button>
                        </c:if>
                        <c:if test="${res.type==3}">
                            <button class="btn btn-info btn-sm disabled" style="width:100px;">多图文</button>
                        </c:if>
                    </div>
                </div>
                <div class="col-sm-4">
                </div>
            </div>
            <div id="dz_ti_text" class="form-group">
                <c:if test="${res.type==1}">
                    <label for="dz-id-2" class="col-xs-2 control-label">文本内容：</label>

                    <div class="col-sm-6">
                        <textarea id="dz_res_con" class="form-control" rows="12"
                                  name="content">${res.content}</textarea>
                    </div>
                    <div class="col-sm-4">
                    </div>
                </c:if>
            </div>
        </form>
        <div class="dz-res-main">
            <div id="dz_ti_one">
                <c:if test="${res.type==2}">
                    <c:forEach var="a" items="${res.article}" varStatus="status">
                        <div class="dz-oall dz-one-sty" id="${a.artId}">
                            <div id="tit">${a.title}</div>
                            <img src="<%=request.getContextPath()%>${a.picUrl}">

                            <div id="des">${a.description}</div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div id="dz_ti_many">
                <c:if test="${res.type==3}">
                    <c:forEach var="a" items="${res.article}" varStatus="status">
                        <c:if test="${status.index == 0}">
                            <div class="dz-oall dz_many_sty" id="${a.artId}">
                                <img src="<%=request.getContextPath()%>${a.picUrl}">

                                <div id="tit">${a.title}</div>
                            </div>
                        </c:if>
                        <c:if test="${status.index != 0}">
                            <div class="dz-oall dz_many_tty" id="${a.artId}">
                                <div id="tit">${a.title}</div>
                                <img src="<%=request.getContextPath()%>${a.picUrl}">
                            </div>
                            <div class="dz-trash" id="${a.artId}">
                                <span class="glyphicon glyphicon-trash"></span>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div id="dz_timany">
                        <span class="glyphicon glyphicon-plus"></span>图文
                    </div>
                </c:if>
            </div>
        </div>
        <div class="dz-addBtn">
            <button id="dz-addRes" type="button" class="btn btn-primary btn-sm" style="width:100px;">提交</button>
        </div>
        <div id="dz_res_ifm">
            <iframe id="zd_iframe" name="resource_data" height="600px" width="1130px" frameborder="no"
                    border="0"></iframe>
        </div>
    </div>
</body>
</html>
