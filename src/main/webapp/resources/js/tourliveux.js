$(document).ready(function() {
	$("#adder").click(function(event) {
		event.stopPropagation();
	});
	
	$("#adder").click(function(){
		$(".newItem").slideToggle('fast');
	});
});
