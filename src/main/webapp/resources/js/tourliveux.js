$(document).ready(function() {
	// UX
	$("#adder").click(function(event) {
		event.stopPropagation();
	});
	$("#adder").click(function() {
		$(".newItem").slideToggle('fast');
	});
	
	// follow me navigation
	var top = $('#menu').offset().top - parseFloat($('#menu').css('marginTop').replace(/auto/, 0));
	  $(window).scroll(function (event) {
	    var y = $(this).scrollTop();
	    if (y >= top) {
	      $('#menu').addClass('fixed');
	    } else {
	      $('#menu').removeClass('fixed');
	    }
	  });

	// Slug generator
	$('.toSlug').keyup(function() {
		$.ajax({
			type : "POST",
			url : "/utils/slug",
			data : {
				toSlug : $('.toSlug').val()
			},
			success : function(data) {
				$('.theSlug').val(data);
			}
		});
	});
	$('.toRaceSlug').keyup(function() {
		$.ajax({
			type : "POST",
			url : "/utils/slug",
			data : {
				toSlug : $('.toRaceSlug').val()
			},
			success : function(data) {
				$('.theRaceSlug').val(data);
			}
		});
	});

	$('#fileuploadbutton').bind("click", function() {
		$('.fileupload').click();
	});
	$('#fileuploadbutton2').bind("click", function() {
		$('.fileupload2').click();
	});
	$('#fileuploadbutton3').bind("click", function() {
		$('.fileupload3').click();
	});
	$('#fileuploadbutton4').bind("click", function() {
		$('.fileupload4').click();
	});
	
	// color picker
	$('.colorpicker').colorpicker();

	// scroll spy
	$('#tourlive-site').scrollspy();

	// table rowlink
	$('tbody.rowlink').rowlink();
});
