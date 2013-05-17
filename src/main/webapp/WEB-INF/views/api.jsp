<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>All Stages</title>
</head>
<body>
	<h2>Karte der Positionen</h2>

	<div id="map-canvas"></div>
	<div class="row-fluid">
		<div class="span6">
			<c:if test="${not empty valueContainer}">
				<table class="table">
					<tr>
						<th>vcID / pID</th>
						<th>Zeit</th>
						<th>latitude</th>
						<th>longitude</th>
					</tr>
					<c:forEach items="${valueContainer}" var="item">
						<jsp:useBean id="dateValue" class="java.util.Date" />
						<jsp:setProperty name="dateValue" property="time" value="${item.timestamp}" />
						<tr>
							<td>${item.valueContainerId} / ${item.positionData.positionid}</td>
							<td><fmt:formatDate pattern="dd.MM.yyy - HH:mm:ss"
									value="${dateValue}" />
							</td>
							<td>${item.positionData.latitude}</td>
							<td>${item.positionData.longitude}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="span6">
			<c:if test="${not empty videos}">
				<table class="table">
					<tr>
						<th>Video ID</th>
						<th>Zeit</th>
						<th>path</th>
						<th>device</th>
					</tr>
					<c:forEach items="${videos}" var="video">
						<tr>
							<td>${video.videoDataId}</td>
							<td>${video.timestamp}</td>
							<td>${video.device.deviceId}</td>
							<td>${video.videoLocation}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvuSLRcfTLJNtCNdz3wPwgQMEiSuDpnq0&sensor=false">
		
	</script>
	<script type="text/javascript">
		function initialize() {
			
			var myLatLng = new google.maps.LatLng(${current.latitude}, ${current.longitude});
			var mapOptions = {
				zoom : 14,
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
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
</body>
</html>