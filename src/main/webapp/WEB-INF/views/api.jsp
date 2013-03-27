<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>All Stages</title>
</head>
<body>
	<h2>Map</h2>

	<div id="map-canvas"></div>

	<h2>Etappen</h2>
	<table class="table">
		<tr>
			<th>Timestampp</th>
			<th>Long</th>
			<th>Lat</th>
			<th>Alt</th>
			<th>Speed</th>
			<th>Direction</th>
			<th>Incline</th>
		</tr>

		<c:forEach items="${positions}" var="positionData">
			<tr>
				<td>${positionData.timestamp}</td>
				<td>${positionData.longitude}</td>
				<td>${positionData.latitude}</td>
				<td>${positionData.altitude}</td>
				<td>${positionData.speed}</td>
				<td>${positionData.direction}</td>
				<td>${positionData.incline}</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvuSLRcfTLJNtCNdz3wPwgQMEiSuDpnq0&sensor=false">
		
	</script>
	<script type="text/javascript">
		function initialize() {
			var myLatLng = new google.maps.LatLng(47.025206, 9.030762);
			var mapOptions = {
				zoom : 9,
				center : myLatLng,
				mapTypeId : google.maps.MapTypeId.HYBRID
			};

			var map = new google.maps.Map(
					document.getElementById("map-canvas"), mapOptions);
			var flightPlanCoordinates = [
				<c:forEach items="${positions}" var="positionData">
					new google.maps.LatLng(${positionData.latitude}, ${positionData.longitude}),
			    </c:forEach>
			];

			var flightPath = new google.maps.Polyline({
				path : flightPlanCoordinates,
				strokeColor : "#FF0000",
				strokeOpacity : 1.0,
				strokeWeight : 2
			});

			flightPath.setMap(map);
		}

		function initialize2() {
			var mapOptions = {
				center : new google.maps.LatLng(-34.397, 150.644),
				zoom : 8,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(
					document.getElementById("map-canvas"), mapOptions);
			center = new google.maps.LatLng(-34.397, 150.644);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
</body>
</html>