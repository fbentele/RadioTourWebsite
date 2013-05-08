<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tourlive - ${stage.stageName}</title>
</head>
<body>
	<div>
		<c:if test="${not empty first}">
			<div id="top" class="row-fluid">
				<h2 id="top">${stage.stageName} (${stage.distance} km)</h2>
				<p class="lead">Es sind ${first.stageData.distance} km von ${stage.distance} km
					gefahren.</p>
			</div>
		</c:if>
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
		<div id="livebilder" class="row-fluid">
			<c:forEach items="${images}" var="image">
				<div class="span4">
					<h4>${image.device.username}</h4>
					<img width="350" src="<c:url value="${hostname}${image.imageLocation}"/>" />
					<div id="">
						<c:forEach items="${latest}" var="latest">
							<c:if test="${latest.device.deviceId == image.device.deviceId}">
						Überwundene Höhenmeter: <span class="pull-right">${latest.stageData.stageUpAltitude}</span>
								<br />
						Aktuelle Zeit: <span class="pull-right">${latest.stageData.stageTime}</span>
								<br />
						Rennkilometer: <span class="pull-right">${latest.stageData.distance}</span>
								<br />
						Durchschnittliche Geschwindigkeit: <span class="pull-right">${latest.stageData.averageSpeed}</span>
								<br />
							</c:if>
						</c:forEach>
					</div>
					<div id="caption">
						Letzte aktualisierung: <span class="pull-right">${image.timestamp}</span>
					</div>
				</div>
			</c:forEach>
		</div>

		<div id="livevideo" class="row-fluid">
			<c:forEach items="${videos}" var="video">
				<div class="span4">
					<h4>${video.device.username}</h4>
					<video width="320" height="240" controls>
						<source src="${hostname}${video.videoLocation}"
							type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'></source>
					</video>
				</div>
			</c:forEach>
			<c:if test="${not empty stage.adCode}">
				<div class="span4">${stage.adCode}</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<c:if test="${not empty valuecontainers}">
				<div class="span6">
					<h4 id="karte">Karte</h4>
					<div id="map-canvas"></div>
				</div>
			</c:if>
			<c:if test="${not empty liveTickerItems}">
				<div class="span6">
					<h4 id="liveticker">Liveticker</h4>
					<p>Hier stehen jeweils die aktuellsten News zum Rennen</p>
					<dl class="dl-horizontal">
						<c:forEach items="${liveTickerItems}" var="lti">
							<dt>${lti.timestamp}</dt>
							<dd>${lti.news}</dd>
						</c:forEach>
						<dt>
							<img src="<c:url value="/resources/img/loading.gif"/>" />
						</dt>
					</dl>
				</div>
			</c:if>
		</div>

		<c:if test="${not empty situation}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="rennsituation">Rennsituation</h4>
					<p>Zum Zeitpunkt: ${situation.timestamp} und Rennkilometer:
						${first.stageData.distance} km</p>
					<c:forEach items="${situation.situation}" var="sit">
						<div class="span2">
							<h4>${sit.groupName}</h4>
							<img src="<c:url value="/resources/img/driver_${sit.groupSize}.png"/>" /><br />
							Fahrer: <br /> ${sit.handicaptime}
							<ul>
								<c:forEach items="${sit.drivernumber}" var="startNr">
									<li id="testa"><a id="rider${startNr}" class="riders" href="#"
										data-toggle="tooltip">${startNr}</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty riders}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="rangliste">Live - Rangliste</h4>
					<table id="ridertable" class="table table-hover">
						<thead>
							<tr>
								<th style="width: 10%">Start #</th>
								<th style="width: 10%">Rang</th>
								<th style="width: 40%">Name</th>
								<th style="width: 10%">Team</th>
								<th style="width: 10%">Land</th>
								<th style="width: 10%">Zeit</th>
								<th style="width: 10%">Rückstand:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${riders}" var="rider">
								<tr>
									<td>${rider.startNr}</td>
									<td>${rider.startNr}</td>
									<td>${rider.name}</td>
									<td>${rider.teamShort}</td>
									<td>${rider.country}</td>
									<td>${rider.timeVirt}</td>
									<td>${rider.timeRueck}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty marchtable}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="marschtabelle">Marschtabelle</h4>
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>Höhe</th>
								<th colspan="2">Distanz (km)</th>
								<th>Ortschaft</th>
								<th>Werbekolonne</th>
								<th>Zeit schnell</th>
								<th>Zeit mittel</th>
								<th>Zeit langsam</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${marchtable}" var="mti">
								<tr <c:if test="${first.stageData.distance > mti.distance}">class="success"</c:if>>

									<td><c:if test="${not empty mti.icon}">
											<img width="20px" src="/resources/img/${mti.icon}.png" />
										</c:if></td>
									<td>${mti.altitude}</td>
									<td>${mti.distance}</td>
									<td>${mti.distanceToGo}</td>
									<td>${mti.ortschaft}</td>
									<td>${mti.werbekolonne}</td>
									<td>${mti.raceFast}</td>
									<td>${mti.raceMedium}</td>
									<td>${mti.raceSlow }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>

	<script type="text/javascript" src="<c:url value="/resources/js/raphael-min.js" />"></script>

	<!-- Strecken / Hoehenprofil  -->
	<c:if test="${not empty stage.stageProfileImage}">

		<script type="text/javascript">
		var streckencanvas = Raphael("strecken-canvas", 940, 350);
		<c:choose>
			<c:when test="${not empty latest}">
				<c:forEach items="${latest}" var="latest">
					var m = ${latest.stageData.distance}*870/${stage.distance}+50;
					var drawstring = "M" + m + ",330L"+m+",135";
					var line_${latest.device.deviceId} = streckencanvas.path(drawstring).attr({"stroke": "${latest.device.color}", "stroke-width":"2"});
				</c:forEach>
			</c:when>
			<c:otherwise>
				var line_default = streckencanvas.path("M50,330L50,135").attr({"stroke": "#ff0", "stroke-width":"2"});
			</c:otherwise>
		</c:choose>
	</script>
	</c:if>

	<!-- Abstandsentwicklung  -->
	<script type="text/javascript">
		var stageDistance = ${stage.distance};
		var canvas = Raphael("abstand-canvas", 940, 220);
		canvas.path("M10,200L10,10").attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});
		canvas.path("M10,200H920").attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});

		canvas.path("M230,190L230,210");
		canvas.path("M450,190L450,210");
		canvas.path("M670,190L670,210");
		canvas.path("M890,190L890,210");
		canvas.text(10,215, "0 " );
		canvas.text(230,215, stageDistance/4);
		canvas.text(450,215, stageDistance/2);
		canvas.text(670,215, stageDistance/4 *3);
		canvas.text(890,215, stageDistance);
					
		canvas.text(50, 10, "Rückstand in m");
		canvas.text(900, 180, "Rennkilometer");
	
		<c:forEach items="${distances}" var="distance">
			var circle = canvas.circle(${distance.stageData.distance}*900/${stage.distance} + 10, 1000/${distance.valueContainerId}, 3).attr({"fill":"${distance.device.color}", "stroke":"${distance.device.color}"});
		</c:forEach>
	</script>

	<!-- Karte -->
	<c:if test="${not empty valuecontainers}">
		<script type="text/javascript"
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvuSLRcfTLJNtCNdz3wPwgQMEiSuDpnq0&sensor=true">
	</script>
		<script type="text/javascript">
		function initialize() {
			
			var myLatLng = new google.maps.LatLng(${first.positionData.latitude}, ${first.positionData.longitude});
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
				<c:if test="${not empty valuecontainer.positionData.latitude}">
					${valuecontainer.device.username}.push(new google.maps.LatLng(${valuecontainer.positionData.latitude}, ${valuecontainer.positionData.longitude}))
			 	</c:if>
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