<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>关键字管理</title>
    <style type="text/css">
    </style>
    <script type="text/javascript">

        function deleteMessage(id){
            $.ajax({
                url:"<%=basePath%>3ti/keyword/delete",
                type:"POST",
                data:"id="+id,
                success:function(data){
                    data? $("#network"+id).fadeOut("slow",function(){
                        window.location.reload();
                    }):alert("删除失败！");

                }
            });
        }
        function audit(id){
            $.ajax({
                url:"<%=basePath%>3ti/keyword/audit",
                type:"POST",
                data:"id="+id,
                success:function(data){
                    if(data)
                    {
                        alert("修改成功");
                    }else
                    {
                        alert("修改失败");
                    }
                    window.location.reload();
                }
            });
        }

        var isCheck = true;
        //反选
        function selectOther(){
            var arrBox = $("#formDiv input:checkbox");
            for (var i = 0; i < arrBox.length; i++) {
                arrBox.get(i).checked = !arrBox.get(i).checked;
            }
            isCheck = !isCheck;
        }


        //全选

        function Allselect(){

            var arrBox = $("#formDiv input:checkbox");
            for (var i = 0; i < arrBox.length; i++) {
                arrBox.get(i).checked =true;
            }

        }

        function isSelect(){

            if($("#formDiv input:checked").length<=0){

                alert("请选择要删除的！");
            }

            else if($("#formDiv input:checkbox").length>0){


                if(confirm("确定要删除吗？"))
                {
                    return true;
                }else
                {
                    return false;
                }


                return true;
            }
            return false;
        }
        <%--isOK = ${falg}--%>
                <%--$(document).ready(function()--%>
                <%--{--%>
                    <%--if(isOK!=null||isOK!="")--%>
                    <%--{--%>
                        <%--if(isOK)--%>
                        <%--{--%>
                            <%--alert("操作成功!")--%>
                        <%--}else--%>
                        <%--{--%>
                            <%--alert("操作失败!")--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>

    </script>
</head>
<body>
<div class="dz-head-bg"></div>
<div class="panel panel-primary dz-main container">
    <div class="panel-heading">
        <!-- title -->
        <h3 class="panel-title dz-title">关键字回复消息</h3>
        <button class="btn pull-right btn-primary"
                onclick="window.location.href='<%=request.getContextPath()%>/3ti/keyword/edit'">
            新建关键字
        </button>
    </div>
    <!-- 内容  -->
    <form action="deleteAll" method="post">
        <div class="panel-body" style="width: 100%">
            <!--数据表格===begin===-->
            <table class="table table-bordered" style="text-align:center;vertical-align: middle;" id="formDiv">
                <col width="40px" />
                <col width="100px" />
                <col width="120px" />
                <col />
                <col />
                <col width="130px" />
                <tr class="default">
                    <td colspan="7" align="left">
                        <button type="button" class="btn btn-success btn-sm" id="selAll" onclick="Allselect();" >全选</button>
                        <button type="button" class="btn btn-success btn-sm" onclick="selectOther()">反选</button>
                        <button id="deleteAll" type="submit" class="btn btn-success btn-sm" onclick="return isSelect();">删除</button>
                    </td>
                </tr>
                <!-- 省份、城市、区域、类别、网点地址、电话信息 -->
                <tr class="active">
                    <th>
                        <%--<input  type="checkbox" name="check" id="check-all"> --%>
                    </th>
                    <th>编号</th>
                    <th>关键字</th>
                    <th>资源ID</th>
                    <th>资源名称</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

                <!-- 网点列表展示开始 -->
                <c:forEach var="n" items="${page.content}" varStatus="in">

                    <tr id="network${n.id}">
                        <td><input  type="checkbox" name="deleteBox"
                                    value="${n.id}"/></td>
                        <td>${n.id}</td>
                        <td>${n.wxKey}</td>
                        <td>${n.resId}</td>
                        <td>${n.resName}</td>
                        <td>
                            <c:if test="${n.status==0}">已禁用</c:if>
                            <c:if test="${n.status==1}">已使用</c:if>
                        </td>
                        <td style="overflow: visible;">
                            <div class="btn-group">
                                <button class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown">操作</button>
                                <ul class="dropdown-menu">
                                    <li><a href="<%=basePath%>3ti/keyword/edit?flag=2&keywordId=${n.id}">修改</a></li>
                                    <li><a onclick="confirm('确认删除该记录?')?deleteMessage(${n.id}):''">删除</a></li>
                                    <c:if test="${n.status==0}">
                                        <li><a onclick="confirm('确认使用该关键字?')?audit(${n.id}):''">使用</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${n.status==1}">
                                        <li><a onclick="confirm('确认禁用该关键字?')?audit(${n.id}):''">禁用</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <!--         网点列表展示结束-->
            </table>

            <!--数据表格===end===-->

            <!--表格数据分页===开始===-->
            <ul class="pagination" >
                <input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}"/>
                <c:if test="${page.number>0}">
                    <li ><a href="<%=basePath%>3ti/keyword/index?pageNumber=${(page.number+1)-1}&sort=${page.sort}">&laquo;</a></li>
                </c:if>

                <c:forEach begin="1" end="${page.totalPages}" var="index">
                    <c:if test="${pageNumber==index }">
                        <li class="active"><a href="<%=basePath%>3ti/keyword/index?pageNumber=${index}&sort=${page.sort}">${index}</a></li>
                    </c:if>
                    <c:if test="${pageNumber!=index }">
                        <li><a href="<%=basePath%>3ti/keyword/index?pageNumber=${index}&sort=${page.sort}">${index}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${page.number<(page.totalPages-1)}">
                    <li><a  href="<%=basePath%>3ti/keyword/index?pageNumber=${(page.number+1)+1}&sort=${page.sort}">&raquo;</a></li>
                </c:if>
                <li><a style="margin-left: 20px;">${page.totalElements}条数据/共${page.totalPages}页</a>
                </li>
            </ul>
            <!--表格数据分页===end===-->
        </div>
    </form>
</div>

</body>
</html>
