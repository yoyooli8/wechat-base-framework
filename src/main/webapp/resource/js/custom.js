$(document).ready(function() { 
	$('a.popup').click(function() {					
		var popupid = $(this).attr('rel');
		$('#' + popupid).fadeIn();
		$('#bg').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
		var popuptopmargin = ($('#' + popupid).height()) / 2;
		var popupleftmargin = ($('#' + popupid).width()) / 2;
		$('#' + popupid).css({
			'margin-top' : -popuptopmargin,
			'margin-left' : -popupleftmargin
		});
	});
	
	$('#bg').click(function() {	
		$('#bg, #dialog').fadeOut();
			return false;
	});
});