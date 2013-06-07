<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page trimDirectiveWhitespaces="true"%>

<html>
<head>
<title>Tourlive - ${stage.stageName}</title>
<!-- <meta http-equiv="refresh" content="30;"> -->
</head>
<body>
	<div>
		<div id="top" class="row-fluid">
			<h2 id="top">${stage.stageName} (${stage.distance} km)</h2>
			<div class="span5">
				<p class="lead">
					<spring:message code="label.stage.distancestatus1" />
					&nbsp;${first.stageData.distance} km
					<spring:message code="label.stage.distancestatus2" />
					&nbsp;${stage.distance} km
					<spring:message code="label.stage.distancestatus3" />
				</p>
			</div>
			<div class="span6 pull-right">
				<spring:message code="label.stage.actualTime" />
				:
				<jsp:useBean id="dateValue" class="java.util.Date" />
				<jsp:setProperty name="dateValue" property="time" value="${limit}" />
				<fmt:formatDate value="${dateValue}" pattern="HH:mm:ss" />
				<br />
				<div id="timenav" class="btn-group">
					<a href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 1800000}"
						class="btn btn-mini visible-desktop">-30min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 600000}"
						class="btn btn-mini">-10min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 300000}"
						class="btn btn-mini">-5min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit - 60000}"
						class="btn btn-mini">-1min</a> <a href="/race/${raceSlug}/stage/${stage.stageSlug}/"
						class="btn btn-mini"><spring:message code="label.stage.now" /></a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 60000}"
						class="btn btn-mini">+1min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 300000}"
						class="btn btn-mini">+5min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 600000}"
						class="btn btn-mini">+10min</a> <a
						href="/race/${raceSlug}/stage/${stage.stageSlug}/${limit + 1800000}"
						class="btn btn-mini visible-desktop">+ 30min</a>
				</div>
			</div>
		</div>
		<c:if test="${not empty stage.stageProfileImage}">
			<div class="row-fluid">
				<div class="span12 border">
					<h4 id="streckenprofil">
						<spring:message code="label.stage.stageProfile" />
					</h4>
					<div id="image-drawing-wrapper">
						<img width="940" height="350" src="${hostname}${stage.stageProfileImage}" />
						<div id="strecken-canvas"></div>
					</div>
					<div id="positionbar" class="progress progress-striped active">
						<div id="progress" class="bar" style="width: 0%;"></div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty valuecontainers}">
			<c:if test="${fn:length(latest) > 1}">
				<div class="row-fluid">
					<div class="span12 border">
						<h4 id="abstand">
							<spring:message code="label.stage.deficiteTime" />
						</h4>
						<div id="abstand-canvas"></div>
					</div>
				</div>
			</c:if>
		</c:if>
		<c:if test="${not empty images}">
			<div id="livestream" class="row-fluid">
				<c:forEach items="${images}" var="image">
					<div class="span4 border">
						<h4>${image.device.labelName}</h4>
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
		</c:if>
		<c:if test="${not empty videos}">
			<div id="livevideo" class="row-fluid">
				<c:forEach items="${videos}" var="video">
					<div class="span5 border">
						<h4>${video.device.labelName}</h4>
						<div id="video-drawing-wrapper">
							<canvas id="videocanvas${video.videoDataId}" class="videocanvas"></canvas>
							<video id="video${video.videoDataId}" width="320" height="240" autoplay controls muted>
								<source id="mp4" src="${hostname}${video.videoLocation}.mp4"
									type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'></source>
								<source id="ogg" src="${hostname}${video.videoLocation}.ogg" type='video/ogg'></source>
							</video>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<div class="row-fluid">
			<c:if test="${not empty valuecontainers}">
				<div class="span6 border" style="height: 400px;">
					<h4 id="karte">
						<spring:message code="label.stage.map" />
					</h4>
					<div id="map-canvas"></div>
				</div>
			</c:if>
			<c:if test="${not empty liveTickerItems}">
				<div class="span6 border" style="height: 400px;">
					<h4 id="liveticker">
						<spring:message code="label.stage.liveticker" />
					</h4>
					<p>
						<spring:message code="label.stage.livetickertext" />
						<br /> <img class="offset1" src="<c:url value="/resources/img/loading.gif"/>" />
					</p>
					<div id="livetickercontainer">
						<ul class="unstyled offset1">
							<c:forEach items="${liveTickerItems}" var="lti">
								<li><b>${lti.timestamp}</b>: ${lti.news}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<c:if test="${not empty stage.adCode}">
				<div id="ads" class="span4 border">${stage.adCode}</div>
			</c:if>
		</div>
		<c:if test="${not empty situation}">
			<div class="row-fluid">
				<div class="span12 border">
					<h4 id="rennsituation">
						<spring:message code="label.stage.raceSituation" />
					</h4>
					<p>
						<spring:message code="label.stage.atTime" />
						&nbsp;${situation.timestamp}
					</p>
					<c:forEach items="${situation.situation}" var="sit">
						<div class="span3 ridergroup">
							<h4>${sit.groupName}</h4>
							<img src="<c:url value="/resources/img/driver_${sit.groupSize}.png"/>" />
							<c:if test="${not sit.isLeader}">
								<br />
								${sit.handicaptime} 
								<c:forEach items="${latest}" var="vc">
									<c:if test="${vc.device.labelName == sit.groupName}">&nbsp;(${vc.deficiteTimeAsString})</c:if>
								</c:forEach>
							</c:if>
							<br />
							<ul class="unstyled">
								<c:forEach items="${sit.riderNumber}" var="situation">
									<c:forEach items="${riders}" var="rider">
										<c:if test="${rider.startNr == situation }">
											<li><span id="rider${situation}"
												class="badge <c:if test="${rider.neo}">badge-success</c:if>
												<c:if test="${rider.timeRueck=='00:00'}">badge-warning</c:if>">${situation}</span>
												<small>${rider.name} - ${rider.teamShort}</small></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
					<div class="span8">
						<spring:message code="label.stage.deficiteToLeader" />
						<br /> <br />
						<spring:message code="label.stage.legend" />
						: <span class="badge"><spring:message code="label.stage.rider" /></span> <span
							class="badge badge-success"><spring:message code="label.stage.neo" /></span> <span
							class="badge badge-warning"><spring:message code="label.stage.leader" /></span>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty riders}">
			<div class="row-fluid">
				<div class="span12 border">
					<h4 id="rangliste">
						<spring:message code="label.stage.liveRanking" />
					</h4>
					<table id="ridertable" class="table table-hover">
						<thead>
							<tr>
								<th style="width: 10%"><spring:message code="label.stage.rank" /></th>
								<th style="width: 10%"><spring:message code="label.stage.startNr" /></th>
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
				<div class="span12 border">
					<h4 id="marschtabelle">
						<spring:message code="label.stage.marchtable" />
					</h4>
					<table id="marchtable" class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="label.stage.altitude" /></th>
								<th><spring:message code="label.stage.distance" /></th>
								<th><spring:message code="label.stage.togo" /></th>
								<th><spring:message code="label.stage.place" /></th>
								<th><spring:message code="label.stage.adTime" /></th>
								<th><spring:message code="label.stage.timeFast" /></th>
								<th><spring:message code="label.stage.timeMedium" /></th>
								<th><spring:message code="label.stage.timeSlow" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${marchtable}" var="mti">
								<c:forEach items="${latest}" var="vc">
									<c:if test="${vc.stageData.distance > mti.distance}">
										<c:set var="color" value="${vc.device.colorAsRGB}" />
									</c:if>
								</c:forEach>
								<tr class="<c:if test="${color != ''}">tagged</c:if>"
									style="background-color: rgba(${color}0.3);">
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
								<c:set var="color" value="" />
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>

	<script type="text/javascript" src="<c:url value="/resources/js/raphael-min.js" />"></script>
	<script type="text/javascript">
		var profilewidth = 940;
		var lineheight=320;
		var lineheightend=135;
		function setScreenParams(){
			var screensize = $(window).width();
			if (screensize >=1200){
				profilewidth = 940;
				lineheight=320;
				lineheightend=135;
			} else if (screensize < 1200 && screensize >= 980 ){
				profilewidth = 768;
				lineheight=265;
				lineheightend= 105;
			} else if (screensize < 980 && screensize >= 768) {
				profilewidth = 588;
				lineheight=200;
				lineheightend= 80;			
			} else if (screensize < 768 && screensize >= 450){
				profilewidth = 520;
				lineheight=180;
				lineheightend=70;
			}  else if (screensize < 450){
				profilewidth = 240;
				lineheight = 150;
				lineheightend = 50;
			}
		}
		setScreenParams();
		window.onresize = function(event) {
			setScreenParams();
		}
	</script>

	<!-- Streckenprofil / Hoehenprofil  -->
	<c:if test="${not empty stage.stageProfileImage}">
		<script type="text/javascript">
		var factor = .925531915;
		$('#positionbar').width(profilewidth*factor);
		$('#positionbar').css( { "width" : (profilewidth*factor), "margin-left" : 50*(profilewidth/1000) } );
		var streckencanvas = Raphael("strecken-canvas", (profilewidth), 350);
		<c:choose>
			<c:when test="${not empty latest}">
				<c:forEach items="${latest}" var="latest">
					var m = ${latest.stageData.distance}*(profilewidth * factor)/${stage.distance}+50;
					var drawstring = "M" + m + "," + lineheight +"L" + m + "," + lineheightend;
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
	<c:if test="${not empty valuecontainers}">
		<script type="text/javascript">
			var factor  = .957446809;
			var factor2 = .978723404;
			var highestdeficite = ${highestdeficite}/1000;
			var heightfactor = 175 / highestdeficite;
			var stageDistance = ${stage.distance};
			var canvas = Raphael("abstand-canvas", profilewidth, 220);
			canvas.path("M10,200L10,10").attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});
			canvas.path("M10,200H" + (profilewidth * factor2)).attr({"stroke": "#000", "stroke-width":"2", 'arrow-end': 'classic-wide-long'});
			canvas.path("M"+ (profilewidth*.244680851) +",190L"+ (profilewidth*.244680851) +",210");
			canvas.path("M"+ (profilewidth*.478723404) +",190L"+ (profilewidth*.478723404) +",210");
			canvas.path("M"+ (profilewidth*.712765957) +",190L"+ (profilewidth*.712765957) +",210");
			canvas.path("M" + (profilewidth*.946808511)+",190L"+(profilewidth*.946808511)+",210");
			canvas.path("M5,25L15,25");
			canvas.text(10,215, "0 " );
			canvas.text((profilewidth*.244680851),215, Math.round(stageDistance/4));
			canvas.text((profilewidth*.478723404),215, Math.round(stageDistance/2));
			canvas.text((profilewidth*.712765957),215, Math.round(stageDistance/4 *3));
			canvas.text((profilewidth*.946808511),215, Math.round(stageDistance));
			canvas.text(25,25, Math.round(highestdeficite) + " s");
			canvas.text(50, 10, "<spring:message code="label.stage.deficiteInS" />");
			canvas.text((factor * profilewidth), 180, "<spring:message code="label.stage.raceKm" />");
			<c:forEach items="${valuecontainers}" var="vc">
				<c:if test="${vc.deficiteTime > 0}">
					canvas.circle(${vc.stageData.distance}*(factor * profilewidth)/${stage.distance} + 10, 200 - ((${vc.deficiteTime}/1000)*heightfactor), 3).attr({"fill":"${vc.device.color}", "stroke":"${vc.device.color}"});
				</c:if>
			</c:forEach>
		</script>
	</c:if>

	<!-- Video -->
	<c:if test="${not empty videos}">
		<script type="text/javascript">
			<c:forEach items="${videos}" var="video">
				var videoPlayer${video.videoDataId} = document.getElementById('video${video.videoDataId}');
				videoPlayer${video.videoDataId}.addEventListener('ended', function(){
					loadNext(videoPlayer${video.videoDataId});
				});
				
				document.addEventListener('DOMContentLoaded', function(){
				    var v = document.getElementById('video${video.videoDataId}');
				    var canvas = document.getElementById('videocanvas${video.videoDataId}');
			    	var context = canvas.getContext('2d');
				    var x      = 0;
				    var y      = 0;
				    var width  = 320;
				    var height = 240;
				    var cx     = x + 0.5 * width;
				    var cy     = y + 0.5 * height;
				   
					var rotation = ${video.rotation};
					context.rotate(rotation*Math.PI/180);
				    context.fillStyle = "#ffffff";
			    	context.fillRect(x, y, width, height);
			    	context.translate(cx, cy);
			    	
				    v.addEventListener('play', function(){
				        draw(this,context,width,height);
				    },false);
				},false);

				function draw(v,c,w,h) {
				    if(v.paused || v.ended) return false;
				    c.drawImage(v,-0.5 * w,-0.5 * h,w,h);
				    setTimeout(draw,20,v,c,w,h);
				}
			</c:forEach>
			
			function loadNext(videoPlayer){
				$.ajax({
					type : "POST",
					dataType: "json",
					url : "/race/${raceSlug}/stage/${stage.stageSlug}/nextvideo",
					data : {
						deviceId : '${videos[0].device.deviceId}',
						afterId: videoPlayer.id.substring(5),
					},
					success : function(data) {
						if (data){
							console.log('new video');
							console.log(data.videoLocation);
							$('#mp4').attr('src', '${hostname}'+ data.videoLocation + '.mp4');
							$('#ogg').attr('src', '${hostname}'+ data.videoLocation + '.ogg');
							videoPlayer.id= "video" + data.videoDataId;
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
				var device_${device.deviceId} = [];
			</c:forEach>
			<c:forEach items="${valuecontainers}" var="valuecontainer">
				<c:if test="${not empty valuecontainer.positionData.latitude}">
					device_${valuecontainer.device.deviceId}.push(new google.maps.LatLng(${valuecontainer.positionData.latitude}, ${valuecontainer.positionData.longitude}))
			 	</c:if>
			</c:forEach>
			<c:forEach items="${devices}" var="device">
				var flightPath_${device.deviceId} = new google.maps.Polyline({
					path : device_${device.deviceId},
					strokeColor : "${device.color}",
					strokeOpacity : 1.0,
					strokeWeight : 2
				});
				flightPath_${device.deviceId}.setMap(map);
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
		var marchtableline = $('#marchtable > tbody > tr[class="tagged"]').length;
		if (marchtableline < 4){
			marchtableline = -3;
		}
		var oTable = $('#marchtable').dataTable( {
        	"oLanguage": {
            	"sLengthMenu": "_MENU_ <spring:message code="label.stage.recordsperpage"/>",
            	"sZeroRecords": "<spring:message code="label.stage.norecords"/>",
            	"sInfo": "_START_ - _END_ <spring:message code="label.stage.of"/> _TOTAL_ <spring:message code="label.stage.places"/>",
            	"sInfoEmpty": " 0 - 0 <spring:message code="label.stage.of"/> 0",
            	"sInfoFiltered": "(<spring:message code="label.stage.filtered"/> _MAX_ )",
        		"sSearch": "<spring:message code="label.stage.search"/>"
        	},
        	"sScrollY": "200px",
    		"sDom": "frtiS",
    		"bDeferRender": true,
    		"fnInitComplete": function () {
    			this.fnSettings().oScroller.fnScrollToRow( marchtableline + 2 );
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
	  		var pct = (offset / wrapper.offsetWidth) * ${stage.distance};
	  		window.location = "/race/${raceSlug}/stage/${stage.stageSlug}/km/"+pct;
		}, false);
	</script>

</body>
</html>