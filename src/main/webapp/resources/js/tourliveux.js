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
});
