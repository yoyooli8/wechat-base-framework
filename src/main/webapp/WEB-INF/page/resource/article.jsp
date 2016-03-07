<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css">
<script src="<%=request.getContextPath()%>/resource/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-wysiwyg.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.hotkeys.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	//添加回复链接
	$("#dz_re_url").click(function() {
		$("#dz_zw_url").show();
		$("#dz_zw_eidt").hide();
	});
	//添加回复图文
	$("#dz_re_ti").click(function() {
		$("#dz_zw_url").hide();
		$("#dz_zw_eidt").show();
	});
	
	//图片
	$("#dz-image").click(function() {
		$("#zd_iframe").attr("src", "<%=request.getContextPath()%>/3ti/resource/imageList");
		$("#dz_res_ifm").show();
		
		$("#dz_the_id").attr("disabled", "disabled");
	});
	
	//提交数据
	$("#dz-addSmt").click(function() {
		var t = $("#dz_res_title").val();
		var i = $("#dz_img_url").val();
		var d = $("#dz_tt_text").val();
		var u = $("#dz_atc_url").val();
		
		if(!t) {
			alert("请输入合法的标题！");return;
		}else if(t.length > 100) {
			alert("标题不能超过100个字符！");return;
		}
		if(!i) {
			alert("请输入合法的图片！");return;
		}
		if(d && d.length > 500) {
			alert("描述信息不能超过500个字符！");return;
		}
		if(!u) {
			//alert("请输入合法的回复链接！");return;
		}else {
			var url = new RegExp("((^http)|(^https)|(^ftp)):\/\/(\\w)+\.(\\w)+");
			if(!url.test(u)) {
				alert("请输入合法的URL，必须以“http://”或者“https://”开头！");return;
			}
			if(u.length > 1000) {
				alert("回复链接不能超过1000个字符！");return;
			}
			$("#art_url").val(u);
		}
		
		$("#dz_form").submit();
		
		//同时关闭窗口
		closeWins();
	});
});

//添加图片
document.addImage = function(url) {
	var cn = "<%=request.getContextPath()%>";
	$("#dz_img_url").val(url);
	$("#dz_res_image").attr("src", cn+url);
	
	//隐藏图片页
	document.closeWin();
};

//关闭窗口
function closeWins() {
	parent.document.closeWin();
}
//关闭窗口
document.closeWin  = function() {
	$("#dz_the_id").removeAttr("disabled");
	$("#dz_res_ifm").hide();
};
</script>
<style type="text/css">
.container{
	width: 1130px;
}
body{
	background-color: #FFFFFF;
	height: 600px;
	padding-top: 20px;
}
#editor {
	overflow: auto;
	height: 500px;
	border: 1px solid #CCCCCC;
}
.dz-tool{
	background-color: #EEEEEE;
	padding: 1px;
	border: 1px solid #CCCCCC;
}
.dz-editor{
	width: 560px;
	display: none;
}
/**/
.dz-art-main{
}
#dz-image{
	width: 360px;
	height: 200px;
	background-color: #DDDDDD;
}
#dz-image img{
	width: 360px;
	height: 200px;
}
#dz-image:HOVER {
	background-color: #CCCCCC;
	cursor: pointer;
}
/*添加图片ifm*/
#dz_res_ifm{
	position: absolute;
	z-index: 1000;
	display: none;
	border: 2px solid #0099CC;
	box-shadow: 4px 8px 5px #888888;
	top: 0;
	left: 100px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-5 dz-art-main">
				<form id="dz_form" class="form-horizontal" action="<%=request.getContextPath()%>/3ti/article/save" method="post">
					<div>
						<input type="hidden" name="artId" value='${art.artId}'/>
						<input type="hidden" name="resourceId" value='${resourceId}'/>
						<input type="hidden" name="infoId" value='${art.infoId}'/>
						<input type="hidden" name="url" id="art_url"/>
						<input type="hidden" name="infoText" value='' id="art_text"/>
					</div>
					<div style="width:360px;">
						<input type="text" placeholder="标题" class="form-control" id="dz_res_title" name="title" value='${art.title}'>
					</div>
					<div id="dz-image">
						<input id="dz_img_url" type="hidden" name="picUrl" value="${art.picUrl}"/>
						<img id="dz_res_image" src="<%=request.getContextPath()%>${art.picUrl}">
					</div>
					<div style="width:360px;" id="dz_desc_div">
						<textarea id="dz_tt_text" placeholder="描述信息" class="form-control" rows="3" name="description" style="resize:none;">${art.description}</textarea>
					</div>
					<div style="width:360px;">
						<button id="dz_re_url" type="button" class="btn btn-info btn-sm" style="width:100px;">添加回复链接</button>
						<!-- <button id="dz_re_ti" type="button" class="btn btn-info btn-sm" style="width:100px;">添加回复正文</button> -->
					</div>
				</form>
				<div style="margin-top:60px;">
					<button id="dz-addSmt" type="button" class="btn btn-primary btn-sm" style="width:100px;">保存</button>
				</div>
				<div id="dz_res_ifm">
					<iframe id="zd_iframe" name="image_data" height="500px" width="880px" frameborder="no" border="0"></iframe>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="row" id="dz_zw_url" <c:if test="${art==null}">style="display:none;"</c:if>>
					<label class="col-xs-2 control-label">URL：</label>
					<div class="col-xs-8">
						<input type="text" class="form-control" id="dz_atc_url" value='${art.url}'>
					</div>
				</div>
				<div class="row dz-editor" id="dz_zw_eidt">
					<div class="dz-tool">
						<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-toggle="dropdown" data-original-title="Font"><i class="glyphicon glyphicon-font"></i><b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a data-edit="fontName Serif" style="font-family:'Serif'">Serif</a></li>
									<li><a data-edit="fontName Sans" style="font-family:'Sans'">Sans</a></li>
									<li><a data-edit="fontName Arial" style="font-family:'Arial'">Arial</a></li>
									<li><a data-edit="fontName Arial Black" style="font-family:'Arial Black'">Arial Black</a></li>
									<li><a data-edit="fontName Courier" style="font-family:'Courier'">Courier</a></li>
									<li><a data-edit="fontName Courier New" style="font-family:'Courier New'">Courier New</a></li>
									<li><a data-edit="fontName Comic Sans MS" style="font-family:'Comic Sans MS'">Comic Sans MS</a></li>
									<li><a data-edit="fontName Helvetica" style="font-family:'Helvetica'">Helvetica</a></li>
									<li><a data-edit="fontName Impact" style="font-family:'Impact'">Impact</a></li>
									<li><a data-edit="fontName Lucida Grande" style="font-family:'Lucida Grande'">Lucida Grande</a></li>
									<li><a data-edit="fontName Lucida Sans" style="font-family:'Lucida Sans'">Lucida Sans</a></li>
									<li><a data-edit="fontName Tahoma" style="font-family:'Tahoma'">Tahoma</a></li>
									<li><a data-edit="fontName Times" style="font-family:'Times'">Times</a></li>
									<li><a data-edit="fontName Times New Roman" style="font-family:'Times New Roman'">Times New Roman</a></li>
									<li><a data-edit="fontName Verdana" style="font-family:'Verdana'">Verdana</a></li>
								</ul>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-toggle="dropdown" data-original-title="Font Size"><i class="glyphicon glyphicon-text-height"></i>&nbsp;<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
									<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
									<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
								</ul>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-edit="bold" data-original-title="Bold (Ctrl/Cmd+B)"><i class="glyphicon glyphicon-bold"></i></a>
								<a class="btn btn-default btn-sm" data-edit="italic" data-original-title="Italic (Ctrl/Cmd+I)"><i class="glyphicon glyphicon-italic"></i></a>
								<a class="btn btn-default btn-sm" data-edit="underline" data-original-title="Underline (Ctrl/Cmd+U)"><i class="glyphicon glyphicon-text-width"></i></a>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-edit="insertunorderedlist" data-original-title="Bullet list"><i class="glyphicon glyphicon-list"></i></a>
								<a class="btn btn-default btn-sm" data-edit="insertorderedlist" data-original-title="Number list"><i class="glyphicon glyphicon-list-alt"></i></a>
								<a class="btn btn-default btn-sm" data-edit="outdent" data-original-title="Reduce indent (Shift+Tab)"><i class="glyphicon glyphicon-indent-right"></i></a>
								<a class="btn btn-default btn-sm" data-edit="indent" data-original-title="Indent (Tab)"><i class="glyphicon glyphicon-indent-left"></i></a>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-toggle="dropdown" data-original-title="Hyperlink"><i class="glyphicon glyphicon-link"></i></a>
								<div class="dropdown-menu input-append">
									<input class="span2" placeholder="URL" type="text" data-edit="createLink">
									<button class="btn" type="button">Add</button>
								</div>
								<a class="btn btn-default btn-sm" data-edit="unlink" data-original-title="Remove Hyperlink"><i class="glyphicon glyphicon-resize-full"></i></a>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" id="pictureBtn" data-original-title="Insert picture (or just drag &amp; drop)"><i class="glyphicon glyphicon-picture"></i></a>
							</div>
						</div>
						<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-edit="justifyleft" data-original-title="Align Left (Ctrl/Cmd+L)"><i class="glyphicon glyphicon-align-left"></i></a>
								<a class="btn btn-default btn-sm" data-edit="justifycenter" data-original-title="Center (Ctrl/Cmd+E)"><i class="glyphicon glyphicon-align-center"></i></a>
								<a class="btn btn-default btn-sm" data-edit="justifyright" data-original-title="Align Right (Ctrl/Cmd+R)"><i class="glyphicon glyphicon-align-right"></i></a>
								<a class="btn btn-default btn-sm" data-edit="justifyfull" data-original-title="Justify (Ctrl/Cmd+J)"><i class="glyphicon glyphicon-align-justify"></i></a>
							</div>
							<div class="btn-group">
								<a class="btn btn-default btn-sm" data-edit="undo" data-original-title="Undo (Ctrl/Cmd+Z)"><i class="glyphicon glyphicon-arrow-left"></i></a>
								<a class="btn btn-default btn-sm" data-edit="redo" data-original-title="Redo (Ctrl/Cmd+Y)"><i class="glyphicon glyphicon-arrow-right"></i></a>
							</div>
						</div>
					</div>
				    <div id="editor" style="outline:none;"></div>
				</div>
			</div>
		</div>
	</div>
	<div style="position:fixed; background-color:#DDDDDD; width:100%; text-align:center; bottom:0;">
		<button id="dz_the_id" type="button" class="btn btn-info btn-sm" style="width:100px;" onclick="closeWins();">关闭</button>
	</div>
</body>
</html>