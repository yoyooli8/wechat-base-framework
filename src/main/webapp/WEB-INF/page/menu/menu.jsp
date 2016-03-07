<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <!-- 头部导航栏 -->
    <jsp:include page="../header.jsp"></jsp:include>
    <title>菜单管理</title>
    <style type="text/css">
        .dz-menu {
            height: 523px;
            padding-right: 10px;
            border-right: 1px solid #DDDDDD;
        }

        .dz-menu span {
            float: right;
            display: inline-block;
            margin: 6px;
            cursor: pointer;
        }

        .dz-me-zo {
            line-height: 30px;
            margin-top: 3px;
            padding-left: 10px;
            background-color: #FFFFFF;
            border: 1px solid #DDDDDD;
        }

        .dz-me-zi > div {
            margin: 2px 0 2px 40px;
            line-height: 25px;
            padding-right: 1px;
            border-bottom: 1px solid #DDDDDD;
        }

        .dz-menu-info {
            height: 120px;
            padding-top: 20px;
            margin-right: 45px;
            background-color: #EEEEEE;
            border: 1px solid #CCCCCC;
            border-radius: 10px;
        }

        .dz-me-he {
            height: 30px;
            margin-right: 50px;
            border-bottom: 1px solid #CCCCCC;
            margin-bottom: 10px;

        }

        span {
            opacity: 0.5;
        }

        span:HOVER {
            opacity: 1;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            //添加主菜单
            $("#dz_add_ps").click(function () {
                $("#menu_iframe").attr("src", "<%=request.getContextPath()%>/3ti/menu/pInfo");
            });

            //发布
            $("#dz_publish").click(function () {
                if (confirm("菜单发布24小时后，将推送给所有用户，确认发布吗？")) {
                    $("#dz_form").submit();
                }
            });
        });

        //删除
        function deleteMe(i, f) {
            if (confirm("确定删除该菜单吗？")) {
                window.location.href = "<%=request.getContextPath()%>/3ti/menu/delete?meunId=" + i + "&flag=" + f;
            }
        }
        ;

        //修改
        function updateMe(i, f) {
            if (f == 1) {//主菜单
                $("#menu_iframe").attr("src", "<%=request.getContextPath()%>/3ti/menu/pInfo?menuId=" + i);
            } else if (f == 2) {
                $("#menu_iframe").attr("src", "<%=request.getContextPath()%>/3ti/menu/sInfo?menuId=" + i);
            }
        }
        ;

        //添加子菜单
        function addSon(i) {
            $("#menu_iframe").attr("src", "<%=request.getContextPath()%>/3ti/menu/sInfo?parId=" + i);
        }
        ;

        document.showRes = function () {
            $("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/resource/show");
            $("#dz_ifm_div").show();
        };
        //添加资源
        document.addRes = function (id, t, n) {
            frames["menu_data"].document.addRes(id, t, n);
            $("#dz_ifm_div").hide();
        };
    </script>
</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container" style="height: 100%;">
    <div>
        <div class="col-xs-3">
            <div>
                <button id="dz_add_ps" type="button" class="btn btn-info btn-sm"
                        style="width:120px;margin: 5px;">添加主菜单
                </button>
                <c:forEach var="m" items="${ms}" varStatus="status">
                    <ul class="list-group">
                        <li class="list-group-item" style="margin: 0px;">${m.menuName}
                            <div class="pull-right">
                                <a class="glyphicon glyphicon-plus" onclick="addSon(${m.meunId});"></a>
                                <a class="glyphicon glyphicon-pencil" onclick="updateMe(${m.meunId}, ${m.flag});"></a>
                                <a class="glyphicon glyphicon-remove" onclick="deleteMe(${m.meunId},1);"></a>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <c:forEach var="sm" items="${m.subMenu}" varStatus="status">
                                <ul class="list-group">
                                    <li class="list-group-item">${sm.menuName}
                                        <div class="pull-right">
                                            <span class="glyphicon glyphicon-remove"
                                                  onclick="deleteMe(${sm.meunId},2);"></span>
                                            <span class="glyphicon glyphicon-pencil"
                                                  onclick="updateMe(${sm.meunId}, ${sm.flag});"></span>
                                        </div>
                                    </li>
                                </ul>
                            </c:forEach>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
        <div class="col-xs-9">
            <div class="dz-me-he">
                点击左侧菜单项以管理菜单内容。
            </div>
            <div class="dz-menu-ifm">
                <iframe id="menu_iframe" name="menu_data" height="360px" width="800px" frameborder="no"
                        border="0"></iframe>
            </div>
            <div class="dz-menu-info">
                <form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/3ti/menu/publish"
                      method="post">
                    <div style="padding-left:300px; padding-top: 20px;">
                        <button id="dz_publish" type="button" class="btn btn-primary" style="width:200px;">发布</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="dz_ifm_div" style="border: 5px solid #3577B1;position: absolute;top: 120px;right: 300px;display: none;">
    <iframe id="zd_iframe" name="resource_data" height="630px" width="900px" frameborder="no" border="0"></iframe>
</div>

<%--<button id="test">测试</button>--%>
<%--<script type="text/javascript">--%>
<%--var data= "<xml><ToUserName><![CDATA[toUser]]></ToUserName>" +--%>
<%--"<FromUserName><![CDATA[FromUser]]></FromUserName>" +--%>
<%--"<CreateTime>123456789</CreateTime>" +--%>
<%--"<MsgType><![CDATA[event]]></MsgType>" +--%>
<%--"<Event><![CDATA[CLICK]]></Event>" +--%>
<%--"<EventKey><![CDATA[3TI_69]]></EventKey>" +--%>
<%--"</xml>";--%>

<%--$("#test").click(function(){--%>
<%--$.ajax({--%>
<%--url:"<%=basePath%>weixin/test",--%>
<%--data:data,--%>
<%--dataType:"xml",--%>
<%--type:"POST",--%>
<%--success:function(data){--%>
<%--console.log(data);--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--</script>--%>
</body>
</html>
