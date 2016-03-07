<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>大转盘游戏</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>/resource/css/web-base.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resource/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jQueryRotate_min.js"></script>
<%      
response.setHeader("Cache-Control", "no-cache");         
response.setHeader("Cache-Control", "no-store");        
response.setDateHeader("Expires", 0);
response.setHeader("Pragma", "no-cache");
%>
<script type="text/javascript">
var prompt = "${prompt}";
var info = null;
var angle = null;

//中奖信息滚动
var tCunt = 1;
var c_div = 0;

var awaId = null;
var winner = null;
$(document).ready(function() {
	//发送ajax请求，查看用户积分情况并加载转盘信息
	$.ajax({
		url: "<%=request.getContextPath()%>/web/zhuanpan/process?openId=${openId}&name=${name}",
		type: "GET",
		dataType: "json",
		success: function(pg) {
			angle = pg.angle;
			winner = pg.winner;
			info = pg.info;
			awaId = pg.awaId;
			
			if(pg.noEnough) {
				alert("您的积分不足，快去赚些积分再来玩吧！");
			}else {
				$("#begin").show();
			}
		},
		error: function() {
			alert("读取游戏信息失败，请刷新页面！");
		}
	});
	
	//指针转动事件
	$("#begin").click(function() {
		$(this).hide();
		
		$("#zhizhen").rotate({
			duration: 6000,//转动时间间隔（转动速度）
			angle: 3600 + angle,  //开始角度
//			angle: 3600 + 100,  //开始角度
			animateTo: angle, //转动角度
			callback: function(){ //回调函数
				$.ajax({
					url: "<%=request.getContextPath()%>/web/zhuanpan/winner?openId=${openId}&name=${name}&awaId=" + awaId +"&angle=" + angle + "&winner=" + winner,
					type: "GET"
				});
				
				//提示信息
				if(confirm(info + "\n\n试试手气，再来玩一次吗？")) {
					window.location.href = "<%=request.getContextPath()%>/web/zhuanpan/index?openId=${openId}&name=${name}&reload=reload";
				}
			}
		});
	});
	
	if(angle < 0) {
		alert(info);return;
	}
	
	//提示信息
	if(prompt) {
		alert(prompt);
	}
	
	//实时中奖信息滚动
	c_div = $("#dz_awaed div").length;
	if(c_div > 2) {
		c_div = c_div / 2;
		
		window.setInterval(function() {
			if(tCunt >= c_div) {
				tCunt = 1;
				$("#dz_awaed").animate({"margin-top": "0"}, 0);
			}else {
				//翻滚动画
				$("#dz_awaed").animate({"margin-top": -45*tCunt + "px"}, 1000);
				tCunt++;
			}
		}, 3000);
	}
});
</script>
<style type="text/css">
.dz-btn-div{
	position: absolute;
	width: 100%;
	max-width: 640px;
}
.dz-button{
	width: 60px;
	height: 60px;
	border-radius: 30px;
	margin-left: auto;
	margin-right: auto;
	display: none;
}
.dz-panzi{
	display: block;
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}
#div_zhizhen{
	position: absolute;
	max-width: 640px;
	max-height: 640px;
}
.dz-zhizhen{
	display: block;
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}
.dz-line{
	margin-top: 5px;
	line-height: 16px;
	font-size: 14px;
	border-bottom: 1px solid rgb(204, 153, 0);
}
</style>
</head>
<body>
	<div class="sm_head">
		<img src="<%=request.getContextPath()%>/resource/image/bg-top.png" style="width:100%">
	</div>
	<div class="sm_body">
		<div id="div_zhizhen" style="overflow:hidden; text-align:center;">
			<img id="zhizhen" class="dz-zhizhen" src="<%=request.getContextPath()%>/resource/image/zhizhen.png"/>
		</div>
		<div class="dz-btn-div">
			<img class="dz-zhizhen" src="<%=request.getContextPath()%>/resource/image/beijing.png"/>
			<div id="begin" class="dz-button"></div>
		</div>
		<div>
			<img id="panzi" class="dz-panzi" src="<%=request.getContextPath()%>/resource/image/panzi.png"/>
		</div>
		
		<div style="color: #f0af3c; margin-top:10px; margin-bottom:20px; padding:10px;">
			<div style="background-color:#955e05;border:1px solid #777; line-height:24px;">实时中奖信息</div>
			<div style="height:45px; overflow:hidden;">
				<div id="dz_awaed">
					<c:forEach var="v" items="${ws}" varStatus="status">
						<div class="dz-line">
							<span style="display:inline-block; width:30%;">
								<c:if test="${v.awaRank == 1}">一等奖</c:if>
								<c:if test="${v.awaRank == 2}">二等奖</c:if>
								<c:if test="${v.awaRank == 3}">三等奖</c:if>
							</span>
                            <%--<span style="display:inline-block; width:30%;">${v[1]}</span>${v[2]}--%>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div style="color: #f0af3c; margin-top:10px; margin-bottom:20px; padding:10px;">
			<div style="background-color:#955e05;border:1px solid #777; line-height:24px;">奖品说明</div>
			<div class="dz-line">
				<span style="font-weight:bold;">一等奖：</span>${pg.awardInfo1}
			</div>
			<div class="dz-line">
				<span style="font-weight:bold;">二等奖：</span>${pg.awardInfo2}
			</div>
			<div class="dz-line">
				<span style="font-weight:bold;">三等奖：</span>${pg.awardInfo3}
			</div>
		</div>
	</div>
</body>
</html>