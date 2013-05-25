<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>Tourlive - ${stage.stageName}</title>
<!-- <meta http-equiv="refresh" content="30;"> -->
</head>
<body>
	<div>
		<c:if test="${not empty first}">
			<div id="top" class="row-fluid">
				<h2 id="top">${stage.stageName} (${stage.distance} km)</h2>
				<div class="span5">
					<p class="lead">
						<spring:message code="label.stage.distancestatus1" />
						${first.stageData.distance} km
						<spring:message code="label.stage.distancestatus2" />
						${stage.distance} km
						<spring:message code="label.stage.distancestatus3" />
					</p>
					<div id="positionbar" class="progress progress-striped active">
						<div id="progress" class="bar" style="width: 0%;"></div>
					</div>
				</div>
				<div class="span4 offset1">
					<spring:message code="label.stage.actualTime" />
					:
					<jsp:useBean id="dateValue" class="java.util.Date" />
					<jsp:setProperty name="dateValue" property="time" value="${limit}" />
					<fmt:formatDate value="${dateValue}" pattern="HH:mm:ss" />
					<br />
					<div class="btn-group">
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 300000}">+ 5min</a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 120000}">+ 2min</a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 60000}">+ 1min</a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/"><spring:message
									code="label.stage.now" /></a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 60000}">- 1min</a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 120000}">- 2min</a>
						</div>
						<div class="btn">
							<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 300000}">- 5min</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty stage.stageProfileImage}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="streckenprofil">
						<spring:message code="label.stage.stageProfile" />
					</h4>
					<div id="image-drawing-wrapper">
						<img width="940" height="350" src="${hostname}${stage.stageProfileImage}" />
						<div id="strecken-canvas"></div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty distances && not empty deficitetimes}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="abstand">
						<spring:message code="label.stage.deficiteTime" />
					</h4>
					<div id="abstand-canvas"></div>
				</div>
			</div>
		</c:if>
		<div id="livestream" class="row-fluid">
			<c:forEach items="${images}" var="image">
				<div class="span4">
					<h4>${image.device.username}</h4>
					<img width="350" src="<c:url value="${hostname}${image.imageLocation}"/>" />
					<div id="">
						<c:forEach items="${latest}" var="latest">
							<c:if test="${latest.device.deviceId == image.device.deviceId}">
								<spring:message code="label.stage.altitudeDif" />: <span class="pull-right">${latest.stageData.stageUpAltitude}</span>
								<br />
								<spring:message code="label.stage.timeNow" />: <span class="pull-right">${latest.stageData.stageTime}</span>
								<br />
								<spring:message code="label.stage.raceKm" />: <span class="pull-right">${latest.stageData.distance}</span>
								<br />
								<spring:message code="label.stage.averageSpeed" />: <span class="pull-right">${latest.stageData.averageSpeed}</span>
								<br />
							</c:if>
						</c:forEach>
					</div>
					<div id="caption">
						<spring:message code="label.stage.lastUpdate" />
						: <span class="pull-right">${image.timestamp}</span>
					</div>
				</div>
			</c:forEach>
		</div>

		<div id="livevideo" class="row-fluid">
			<c:forEach items="${videos}" var="video">
				<div class="span5">
					<h4>${video.device.username}</h4>
					<video id="${video.videoDataId}" width="320" height="240" autoplay controls muted>
						<source id="mp4" src="${hostname}${video.videoLocation}.mp4"
							type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'></source>
						<source id="ogg" src="${hostname}${video.videoLocation}.ogg" type='video/ogg'></source>
					</video>
				</div>
			</c:forEach>
			<c:if test="${not empty stage.adCode}">
				<div id="ads" class="span4">${stage.adCode}</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<c:if test="${not empty valuecontainers}">
				<div class="span6">
					<h4 id="karte">
						<spring:message code="label.stage.map" />
					</h4>
					<div id="map-canvas"></div>
				</div>
			</c:if>
			<c:if test="${not empty liveTickerItems}">
				<div class="span6">
					<h4 id="liveticker">
						<spring:message code="label.stage.liveticker" />
					</h4>
					<p>
						<spring:message code="label.stage.livetickertext" />
					</p>
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
					<h4 id="rennsituation">
						<spring:message code="label.stage.raceSituation" />
					</h4>
					<p>
						<spring:message code="label.stage.atTime" />
						: ${situation.timestamp}
						<spring:message code="label.stage.andKm" />
						: ${first.stageData.distance} km
					</p>
					<c:forEach items="${situation.situation}" var="sit">
						<div class="span2">
							<h4>${sit.groupName}</h4>
							<img src="<c:url value="/resources/img/driver_${sit.groupSize}.png"/>" /><br />
							<c:if test="${not sit.isLeader}">
								<spring:message code="label.stage.deficite" />: ${sit.handicaptime} </c:if>
							<br />
							<ul class="unstyled">
								<c:forEach items="${sit.riderNumber}" var="situation">
									<c:forEach items="${riders}" var="rider">
										<c:if test="${rider.startNr == situation }">
											<li><span id="rider${situation}"
												class="badge <c:if test="${rider.neo}">badge-success</c:if> <c:if test="${rider.timeRueck=='00:00'}">badge-warning</c:if> data-toggle="
												tooltip" data-placement="right" title="${rider.name} - ${rider.teamShort}">${situation}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
					<p>
						<spring:message code="label.stage.legend" />
						:<br /> <span class="badge"><spring:message code="label.stage.rider" /></span> <span
							class="badge badge-success"><spring:message code="label.stage.neo" /></span> <span
							class="badge badge-warning"><spring:message code="label.stage.leader" /></span>
					</p>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty riders}">
			<div class="row-fluid">
				<div class="span12">
					<h4 id="rangliste">
						<spring:message code="label.stage.liveRanking" />
					</h4>
					<table id="ridertable" class="table table-hover">
						<thead>
							<tr>
								<th style="width: 10%"><spring:message code="label.stage.startNr" /></th>
								<th style="width: 10%"><spring:message code="label.stage.rank" /></th>
								<th style="width: 40%"><spring:message code="label.stage.riderName" /></th>
								<th style="width: 10%"><spring:message code="label.stage.team" /></th>
								<th style="width: 10%"><spring:message code="label.stage.country" /></th>
								<th style="width: 10%"><spring:message code="label.stage.timeVirt" /></th>
								<th style="width: 10%"><spring:message code="label.stage.deficite" /></th>
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
					<h4 id="marschtabelle">
						<spring:message code="label.stage.marchtable" />
					</h4>
					<table id="marchtable" class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="label.stage.altitude" /></th>
								<th colspan="2"><spring:message code="label.stage.distance" /></th>
								<th><spring:message code="label.stage.place" /></th>
								<th><spring:message code="label.stage.adTime" /></th>
								<th><spring:message code="label.stage.timeFast" /></th>
								<th><spring:message code="label.stage.timeMedium" /></th>
								<th><spring:message code="label.stage.timeSlow" /></th>
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
									<td>${mti.settlement}</td>
									<td>${mti.advertisingColumn}</td>
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

	<!-- Streckenprofil / Hoehenprofil  -->
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
	<c:if test="${not empty distances && not empty deficitetimes}">
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
			canvas.text(50, 10, "<spring:message code="label.stage.deficiteInS" />");
			canvas.text(900, 180, "<spring:message code="label.stage.raceKm" />");
		
			<c:forEach items="${distances}" var="distance">
				canvas.circle(${distance.stageData.distance}*900/${stage.distance} + 10, 200 - <c:out value="${deficitetimes[distance.valueContainerId]}"/>, 3).attr({"fill":"${distance.device.color}", "stroke":"${distance.device.color}"});
			</c:forEach>
		</script>
	</c:if>

	<!-- Video -->
	<c:if test="${not empty videos}">
		<script type="text/javascript">
			<c:forEach items="${videos}" var="video">
				var videoPlayer${video.videoDataId} = document.getElementById('${video.videoDataId}');
				videoPlayer${video.videoDataId}.addEventListener('ended', function(){
					loadNext(videoPlayer${video.videoDataId});
				});
			</c:forEach>
			
			function loadNext(videoPlayer){
				$.ajax({
					type : "POST",
					dataType: "json",
					url : "/race/${raceSlug}/stage/${stage.stageSlug}/nextvideo",
					data : {
						deviceId : '${videos[0].device.deviceId}',
						afterId: videoPlayer.id
					},
					success : function(data) {
						if (data){
							console.log('new video');
							console.log(data.videoLocation);
							$('#mp4').attr('src', '${hostname}'+ data.videoLocation + '.mp4');
							$('#ogg').attr('src', '${hostname}'+ data.videoLocation + '.ogg');
							videoPlayer.id= data.videoDataId;
							videoPlayer.load();
						} else {
							console.log('no new video');
							console.log('i will try again');
							window.setTimeout(function(){loadNext(videoPlayer)},8000);
						}
					}
				});
			}
		</script>
	</c:if>

	<!-- Riders names tooltip -->
	<script>
		<c:forEach items="${riders}" var="rider">
			$('#rider${rider.startNr}').tooltip();
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
			var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
			<c:forEach items="${devices}" var="device">
				var ${device.username} = [];
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

	<!-- Ranking DataTable -->
	<script type="text/javascript">
		$('#ridertable').dataTable( {
        	"oLanguage": {
            	"sLengthMenu": "_MENU_ <spring:message code="label.stage.recordsperpage"/>",
            	"sZeroRecords": "<spring:message code="label.stage.norecords"/>",
            	"sInfo": "_START_ - _END_ <spring:message code="label.stage.of"/> _TOTAL_ <spring:message code="label.stage.rider"/>",
            	"sInfoEmpty": " 0 - 0 <spring:message code="label.stage.of"/> 0",
            	"sInfoFiltered": "(<spring:message code="label.stage.filtered"/> _MAX_ )",
        		"sSearch": "<spring:message code="label.stage.search"/>",
        		"oPaginate":{
        			"sNext": "<spring:message code="label.stage.next"/>",
        			"sPrevious": "<spring:message code="label.stage.previous"/>"
        		}
        	}
    	});
	$.extend($.fn.dataTableExt.oStdClasses, {
		"sWrapper" : "dataTables_wrapper form-inline"
	});
	</script>
	
	<!-- Marchtable DataTable -->
	<script type="text/javascript">
		$('#marchtable').dataTable( {
        	"oLanguage": {
            	"sLengthMenu": "_MENU_ <spring:message code="label.stage.recordsperpage"/>",
            	"sZeroRecords": "<spring:message code="label.stage.norecords"/>",
            	"sInfo": "_START_ - _END_ <spring:message code="label.stage.of"/> _TOTAL_ <spring:message code="label.stage.rider"/>",
            	"sInfoEmpty": " 0 - 0 <spring:message code="label.stage.of"/> 0",
            	"sInfoFiltered": "(<spring:message code="label.stage.filtered"/> _MAX_ )",
        		"sSearch": "<spring:message code="label.stage.search"/>",
        		"oPaginate":{
        			"sNext": "<spring:message code="label.stage.next"/>",
        			"sPrevious": "<spring:message code="label.stage.previous"/>"
        		}
        	}
    	});
	$.extend($.fn.dataTableExt.oStdClasses, {
		"sWrapper" : "dataTables_wrapper form-inline"
	});
	</script>

	<!-- Progressbar Awesomeness -->
	<script type="text/javascript">
		var wrapper = document.getElementById('positionbar');
		var progress = document.getElementById('progress');
		var status = ${first.stageData.distance} * 100 / ${stage.distance};
		progress.style.width = status + "%";		
		wrapper.addEventListener('click', function(e) {
			var offset=e.offsetX==undefined?e.layerX:e.offsetX;
	  		progress.style.width = offset + "px";
	  		var pct = Math.floor((offset / wrapper.offsetWidth) * ${stage.distance});
	  		window.location = "/race/${raceSlug}/stage/${stage.stageSlug}/km/"+pct;
		}, false);
	</script>
</body>
</html>