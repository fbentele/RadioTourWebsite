<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tourlive - ${stage.stageName}</title>
</head>
<body>
	<div class="span10">
		<div class="row-fluid">
			<ul class="nav nav-pills">
				<li><a href="#">Top</a></li>
				<li><a href="#streckenprofil">Streckenprofil</a></li>
				<li><a href="#abstand">Abstandsentwicklung</a></li>
				<li><a href="#livebilder">Livebilder</a></li>
				<li><a href="#liveticker">Liveticker</a></li>
				<li><a href="#map-canvas">Karte</a></li>
				<li><a href="#rennsituation">Rennsituation</a></li>
				<li><a href="#rangliste">Rangliste</a></li>
			</ul>
		</div>
		<div class="row-fluid">
			<h2 id="top">${stage.stageName} (${stage.distance} km)</h2>
			<c:if test="${not empty first.stageData.distance}">
				<p class="lead">Es sind ${first.stageData.distance} km von ${stage.distance} km
					gefahren.</p>
			</c:if>
		</div>
		<c:if test="${not empty stage.stageProfileImage}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="streckenprofil">Streckenprofil</h4>
					<div id="image-drawing-wrapper">
						<img width="940" src="${hostname}${stage.stageProfileImage}" />
						<div id="strecken-canvas"></div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty distances}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="abstand">Abstandsentwicklung</h4>
					<div id="abstand-canvas"></div>

				</div>
			</div>
		</c:if>

		<div class="row-fluid" id="livebilder">
			<c:forEach items="${images}" var="image">
				<div class="span4">
					<h4>${image.device.username}</h4>
					<img width="250" src="<c:url value="${hostname}${image.imageLocation}"/>" />
					<div id="caption">Letzte aktualisierung: ${image.timestamp}</div>
				</div>
			</c:forEach>
		</div>

		<c:if test="${not empty valuecontainers}">
			<div class="row-fluid">
				<div class="span6">
					<h4 id="karte">Karte</h4>
					<div id="map-canvas"></div>
				</div>
				<div class="span6">
					<h4 id="liveticker">Liveticker</h4>
					<p>Hier stehen jeweils die aktuellsten News zum Rennen</p>
					<dl class="dl-horizontal">
						<dt>09:35:12</dt>
						<dd>Fahrer fährt vor</dd>
						<dt>09:35:47</dt>
						<dd>Grosser Sturz im Feld</dd>
						<dt>09:35:59</dt>
						<dd>Nr. 9 wird überholt</dd>
						<dt>09:36:15</dt>
						<dd>Alle fahren schnell</dd>
						<dt>09:36:43</dt>
						<dd>Nr. 12 trink Wasser</dd>
						<dt>09:36:54</dt>
						<dd>Team BMC macht richtig Dampf</dd>
						<dt>09:37:09</dt>
						<dd>Alle anderen auch</dd>
						<dt>
							<img src="<c:url value="/resources/img/loading.gif"/>" />
						</dt>
					</dl>
				</div>
			</div>
		</c:if>
	
	
<!-- 		<div class="row-fluid">
			<div class="span12">
				<h4 id="rennsituation">Rennsituation</h4>
				<table class="table table-hover">
					<tr>
						<th>Feld</th>
						<th>Gruppe</th>
						<th>Spitze</th>
					</tr>
					<tr>
						<td><img src="<c:url value="/resources/img/f5.png"/>" /></td>
						<td><img src="<c:url value="/resources/img/f2.png"/>" /></td>
						<td><img src="<c:url value="/resources/img/f3.png"/>" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<h4 id="rangliste">Rangliste</h4>
				<table class="table table-hover">
					<tr>
						<th>Startnummer</th>
						<th>Rang</th>
						<th>Name</th>
						<th>Team</th>
						<th>Land</th>
						<th>Zeit</th>
					</tr>
					<tr>
						<td>1</td>
						<td>3</td>
						<td>Fabian Cancellara</td>
						<td>BMC</td>
						<td>Sui</td>
						<td>4:23:1</td>
					</tr>
					<tr>
						<td>1</td>
						<td>3</td>
						<td>Fabian Cancellara</td>
						<td>BMC</td>
						<td>Sui</td>
						<td>4:23:1</td>
					</tr>
					<tr>
						<td>1</td>
						<td>3</td>
						<td>Fabian Cancellara</td>
						<td>BMC</td>
						<td>Sui</td>
						<td>4:23:1</td>
					</tr>
					<tr>
						<td>1</td>
						<td>3</td>
						<td>Fabian Cancellara</td>
						<td>BMC</td>
						<td>Sui</td>
						<td>4:23:1</td>
					</tr>
				</table>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<h4 id="marschtabelle">Marschtabelle</h4>
					<table class="table table-hover">
						<tr>
							<th>Höhe</th>
							<th>km</th>
							<th>Ortschaft</th>
							<th>Werbekolonne</th>
							<th>Zeit schnell</th>
							<th>Zeit langsam</th>
						</tr>
						<tr>
							<td>1</td>
							<td>3</td>
							<td>Genf</td>
							<td>13:12</td>
							<td>13:15</td>
							<td>4:23:1</td>
						</tr>
						<tr class="success">
							<td>1</td>
							<td>3</td>
							<td>Bern</td>
							<td>14:12</td>
							<td>14:15</td>
							<td>4:23:1</td>
						</tr>
						<tr>
							<td>1</td>
							<td>3</td>
							<td>Zürich</td>
							<td>15:12</td>
							<td>15:15</td>
							<td>4:23:1</td>
						</tr>
						<tr>
							<td>1</td>
							<td>3</td>
							<td>St. Gallen</td>
							<td>16:12</td>
							<td>16:15</td>
							<td>4:23:1</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	 -->
	<script type="text/javascript" src="<c:url value="/resources/js/raphael-min.js" />"></script>

	<!-- Strecken / Hoehenprofil -->
	<script type="text/javascript">
		var streckencanvas = Raphael("strecken-canvas", 940, 350);
		<c:choose>
			<c:when test="${not empty latest}">
				<c:forEach items="${latest}" var="latest">
					var m = ${latest.stageData.distance}*870/${latest.stageData.distance}+50;
					var drawstring = "M" + m + ",330L"+m+",135";
					var line_${latest.device.deviceId} = streckencanvas.path(drawstring).attr({"stroke": "${latest.device.color}", "stroke-width":"2"});
				</c:forEach>
			</c:when>
			<c:otherwise>
				var line_default = streckencanvas.path("M50,330L50,135").attr({"stroke": "#ff0", "stroke-width":"2"});
			</c:otherwise>
		</c:choose>
	</script>

	<!-- Abstandsentwicklung -->
	<script type="text/javascript">
					var stageDistance = ${stage.distance};
					var canvas = Raphael("abstand-canvas", 940, 220);
					var lineY = canvas.path("M10,200L10,10").attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});
					var lineX = canvas.path("M10,200H920").attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});
					
					var markerLineX50 = canvas.path("M450,190L450,210");
					var markerLineX100 = canvas.path("M900,190L900,210");
					var textMarkerLineX50 = canvas.text(450,215, stageDistance/2);
					var textMarkerLineX100 = canvas.text(900,215, stageDistance);
					
					canvas.text(50, 10, "Rückstand in m");
					canvas.text(900, 180, "Rennkilometer");
					
					<c:forEach items="${distances}" var="distance">
						var circle = canvas.circle(${distance.stageData.distance}*900/${stage.distance} + 10, 80, 3).attr({"fill":"${distance.device.color}", "stroke":"${distance.device.color}"});
					</c:forEach>
	</script>

	<!-- Karte -->
	<c:if test="${not empty valuecontainers}">
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvuSLRcfTLJNtCNdz3wPwgQMEiSuDpnq0&sensor=true">
	</script>
		<script type="text/javascript">
		function initialize() {
			
			var myLatLng = new google.maps.LatLng(${current.positionData.latitude}, ${current.positionData.longitude});
			var mapOptions = {
				zoom : 14,
				center : myLatLng,
				mapTypeId : google.maps.MapTypeId['ROADMAP'],
			};
			
			var map = new google.maps.Map(
					document.getElementById("map-canvas"), mapOptions);
			
			<c:forEach items="${devices}" var="device">
				var ${device.username} = []
			</c:forEach>

			<c:forEach items="${valuecontainers}" var="valuecontainer">
				${valuecontainer.device.username}.push(new google.maps.LatLng(${valuecontainer.positionData.latitude}, ${valuecontainer.positionData.longitude}))
			 </c:forEach>
		
			<c:forEach items="${devices}" var="device">
				var flightPath_${device.username} = new google.maps.Polyline({
					path : ${device.username},
					strokeColor : "${device.color}",
					strokeOpacity : 1.0,
					strokeWeight : 2
				});
				flightPath_${device.username}.setMap(map);

			</c:forEach>
			}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	</c:if>
</body>
</html>