$(document)
		.ready(
				function() {

					// follow me navigation
					var top = $('#menu').offset().top
							- parseFloat($('#menu').css('marginTop').replace(
									/auto/, 0));
					$(window).scroll(function(event) {
						var y = $(this).scrollTop();
						if (y >= top) {
							$('#menu').addClass('fixed');
						} else {
							$('#menu').removeClass('fixed');
						}
					});
					// scroll spy
					$('#tourlive-site').scrollspy();

					// table rowlink
					$('tbody.rowlink').rowlink();
				});
