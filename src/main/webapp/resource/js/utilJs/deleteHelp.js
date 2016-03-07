$(function() {
	//all
	$("#check-all").click(function() {
		var flag = $(this).prop("checked");
		setAllCheckboxState(flag);
		GetSelectItemIds();
	});

	//every Checkbox
	$(".item-check-box").each(function() {
		$(this).click(function() {
			var flag = $(this).prop("checked");
			checkAllCheckBox();
			GetSelectItemIds();
		});
	});

	//Delete operate
	$("#Del_Data").click(function() {
		var ids = $('#selected-productSku').val();
		if (ids.length <= 0) {
			if($("table").find("tr").length>1){
				alert("请选择要删除的信息！");
			}else{
				alert("没有可删除的信息！");
			}
			return;
		} else if (confirm("确认删除选中数据?")) {
			$.post($('#selected-productSku').attr("pageurl"), {
				deleteSkus : ids
			}, function(result) {
				if (result == "success") {
					alert("删除成功!");
					window.location.reload();
					setAllCheckboxState(false);
				} else if(parseInt(result)>0){
					alert(result+"条角色信息在使用，不能删除！");
				}else if(result=="IsLogin"){
					alert("不能删除登录用户！");
				}else{
					alert("删除失败！");
				}
			});
		}
	});

	//Ischeckall
	function checkAllCheckBox() {
		var checkflag = true;
		var count = 0;
		$(".item-check-box").each(function() {
			if ($(this).prop("checked") == false && checkflag) {
				checkflag = false;
			} else {
				count++;
			}
		});

		if (count == 0) {
			checkflag = false;
		}
		$("#check-all").prop("checked", checkflag);
	}

	//Set CheckBox State
	function setAllCheckboxState(flag) {
		$("[type='checkbox']").prop("checked", flag);
	}

	//select checkBox Value
	function GetSelectItemIds() {
		var ids = '';
		$(".item-check-box").each(function() {
			if ($(this).prop("checked")) {
				ids += $(this).val() + ",";
			}
		});
		$('#selected-productSku').val(ids);
	}
});