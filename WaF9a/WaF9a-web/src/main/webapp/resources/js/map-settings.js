!function() {
	"use strict";
	function e() {
		{
			var e = {
				zoom : 11,
				scrollwheel : !1,
				center : new google.maps.LatLng(40.67, -73.94),
				styles : [ {
					featureType : "landscape.natural",
					elementType : "geometry.fill",
					stylers : [ {
						visibility : "on"
					}, {
						color : "#e0efef"
					} ]
				}, {
					featureType : "poi",
					elementType : "geometry.fill",
					stylers : [ {
						visibility : "on"
					}, {
						hue : "#1900ff"
					}, {
						color : "#c0e8e8"
					} ]
				}, {
					featureType : "road",
					elementType : "geometry",
					stylers : [ {
						lightness : 100
					}, {
						visibility : "simplified"
					} ]
				}, {
					featureType : "road",
					elementType : "labels",
					stylers : [ {
						visibility : "off"
					} ]
				}, {
					featureType : "transit.line",
					elementType : "geometry",
					stylers : [ {
						visibility : "on"
					}, {
						lightness : 700
					} ]
				}, {
					featureType : "water",
					elementType : "all",
					stylers : [ {
						color : "#7dcdcd"
					} ]
				} ]
			}, t = document.getElementById("map"), l = new google.maps.Map(t, e);
			new google.maps.Marker({
				position : new google.maps.LatLng(40.67, -73.94),
				map : l,
				title : "JobHunt!"
			})
		}
	}
	google.maps.event.addDomListener(window, "load", e)
}(jQuery);