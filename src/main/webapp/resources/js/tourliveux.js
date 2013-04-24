$(document).ready(function() {
	$("#adder").click(function(event) {
		event.stopPropagation();
	});

	$("#adder").click(function() {
		$(".newItem").slideToggle('fast');
	});

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
	
	$('#fileuploadbutton').bind("click" , function () {
        $('.fileupload').click();
    });
	$('#fileuploadbutton2').bind("click" , function () {
        $('.fileupload2').click();
    });
	
	// datetimepicker
	// http://tarruda.github.io/bootstrap-datetimepicker/
	$.fn.datetimepicker.defaults = {
			  maskInput: true,           // disables the text input mask
			  pickDate: true,            // disables the date picker
			  pickTime: true,            // disables de time picker
			  pick12HourFormat: false,   // enables the 12-hour format time picker
			  pickSeconds: false,         // disables seconds in the time picker
			  startDate: -Infinity,      // set a minimum date
			  endDate: Infinity          // set a maximum date
			};
	$(function() {
		$('#datetimepicker1').datetimepicker({
			language : 'de-CH'
		});
		$('#datetimepicker2').datetimepicker({
			language : 'de-CH'
		});
	});
});
