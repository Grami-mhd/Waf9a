!function() {
	"use strict";
	$(window).load(function() {
		$("#loader").fadeOut(), $("#mask").delay(1e3).fadeOut("slow")
	}), $(document).ready(function() {
		$.slidebars({
			siteClose : !1
		}), $(".sb-toggle-left").click(function() {
			$(this).toggleClass("active")
		})
	}), $(document).ready(function() {
		$("#basic-selecter").fancySelect(), $("#basic-selecter1").fancySelect()
	}), $(document).ready(
			function() {
				$(".campers-all").find("li").click(
						function(e) {
							e.preventDefault(), $(".campers-all").find("li")
									.find("a").removeClass("active"), $(this)
									.find("a").addClass("active");
							var a = $(this).find("a").attr("href");
							$(".testimonial-single").removeClass("active"),
									$(a).addClass("active")
						})
			}), $(".overview-btn").click(function() {
		$(".overview-detail").slideToggle()
	}), $(function() {
		$(".animated").appear(function() {
			var e = $(this), a = e.data("animation");
			if (!e.hasClass("visible")) {
				var i = e.data("animation-delay");
				i ? setTimeout(function() {
					e.addClass(a + " visible")
				}, i) : e.addClass(a + " visible")
			}
		})
	})
}(jQuery);