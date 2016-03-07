function selectAll(){
	if(!$("#check-all").prop("checked")){
		$("#check-all")[0].click();
	}
}
function selectPart(){
	$(".item-check-box").each(function(){
		$(this)[0].click();
	});
}
//Ischeckall
function checkAllCheckBox() {
	var count = 0;
	var all = 0;
	$(".item-check-box").each(function() {
	if(!$(this).prop("checked")) {
			count++;
		}
		all++;
	});
	return count == all;
}
function deleteSelect(){
	if(!checkAllCheckBox()){
		confirm("确认删除选中的商品？")?document.deleteForm.submit():"";
	}else{
		alert("请选中你要删除的商品！");
	};
}