<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2>
		<spring:message code="label.admin.editStage" />
		:
	</h2>
	<p>
		<a class="badge badge-success"
			href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker"><spring:message
				code="label.admin.startLiveticker" /></a>
				<a class="badge badge-success"
			href="/admin/race/${race.raceId}/stage/${stage.stageId}/recalculate"><spring:message
				code="label.admin.recalculateDeficite" /></a>
	</p>
	<div class="row-fluid">
		<form:form method="post" name="stage"
			action="/admin/race/${race.raceId}/stage/edit/${stage.stageId}"
			enctype="multipart/form-data">
			<div class="span4">
				<input type="hidden" name="stageId" value="${stage.stageId}" /> <label for="stageName"><spring:message
						code="label.admin.stageName" /></label> <input type="text" name="stageName" class="toSlug"
					value="${stage.stageName}" tabindex="1" /> <label for="stageSlug"><spring:message
						code="label.admin.stageSlug" /></label> <input type="text" name="stageSlug" class="theSlug"
					value="${stage.stageSlug}" tabindex="2" />
				<div class="input-append">
					<label for="distance"><spring:message code="label.admin.distance" /></label> <input
						type="text" name="distance" value="${stage.distance}" tabindex="3"> <span
						class="add-on">km</span>
				</div>
				<br /> <label for="starttime"><spring:message code="label.admin.starttime" />
					<spring:message code="label.admin.starttimeformat" /></label>
				<div id="datetimepicker1" class="input-append date">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="starttime"
						value="${stage.starttime}" tabindex="5" /> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>
				<label for="endtime"><spring:message code="label.admin.endtime" /> <spring:message
						code="label.admin.starttimeformat" /><c:if test="${not empty timeerror}"><br /><span class="formerror"><spring:message code="label.admin.timeError" /></span></c:if></label>
				<div id="datetimepicker2" class="input-append">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="endtime"
						value="${stage.endtime}" tabindex="6" /> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>
				<label for="offsettime"><spring:message code="label.admin.offsettime" /> (hh:mm:ss)</label>
				<div id="datetimepicker3" class="input-append time">
					<input data-format="hh:mm:ss" type="datetime" name="offsettime" value="${stage.offsettime}" tabindex="7" /><span class="add-on"> <i
						data-time-icon="icon-time"></i></span>
				</div>
				<div>
					<label for="stagetype"><spring:message code="label.admin.stagetype" /></label> <select
						name="stageType">
						<option value="SINGLETIMERACE"
							<c:if test="${stagetype == 'SINGLETIMERACE'}">selected</c:if>>
							<spring:message code="label.stage.type.singeltimerace" />
						</option>
						<option value="GROUPTIMERACE"
							<c:if test="${stagetype == 'GROUPTIMERACE'}">selected</c:if>>
							<spring:message code="label.stage.type.grouptimerace" />
						</option>
						<option value="PROLOG" <c:if test="${stagetype == 'PROLOG'}">selected</c:if>>
							<spring:message code="label.stage.type.prolog" />
						</option>
						<option value="MOUNTAINSTAGE"
							<c:if test="${stagetype == 'MOUNTAINSTAGE'}">selected</c:if>>
							<spring:message code="label.stage.type.mountainstage" />
						</option>
						<option value="FLATSTAGE" <c:if test="${stagetype == 'FLATSTAGE'}">selected</c:if>>
							<spring:message code="label.stage.type.flatstage" />
						</option>
						<option value="OTHER" <c:if test="${stagetype == 'OTHER'}">selected</c:if>>
							<spring:message code="label.stage.type.other" />
						</option>
					</select>
				</div>
				<label for="visible"> <input type="checkbox" name="visible"
					value="true${stage.visible}" <c:if test="${stage.visible==true}">checked</c:if>
					tabindex="7"> <spring:message code="label.admin.visible" />
				</label>
				<button type="submit" class="btn btn-primary" tabindex="11">
					<spring:message code="label.admin.save" />
				</button>
			</div>
			<div class="span8">
				<label for="stageDescription"><spring:message code="label.description" /></label>
				<textarea name="stageDescription" rows="5" class="span12" tabindex="4">${stage.stageDescription}</textarea>
				<div>
					<label for="adCode"><spring:message code="label.admin.htmlForAd" /> (300px X
						200px)</label>
					<textarea name="adCode" rows="4" class="span12" tabindex="8">${stage.adCode}</textarea>
				</div>

				<div>
					<label for="bannerImageFile"><spring:message code="label.admin.uploadBanner" />:
						(940x210px)</label>
					<c:if test="${not empty stage.bannerImage}">
						<img src="${hostname}${stage.bannerImage}" class="img-rounded" />
						<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/bannerimage/delete"><i
							class="icon-trash pull-right"></i></a>
					</c:if>
					<div id="fileuploadbutton" class="btn" tabindex="9">
						<spring:message code="label.admin.chooseImg" />
					</div>
					<br /> <input type="file" accept="image/*" name="bannerImageFile" class="fileupload" />
				</div>
				<div>
					<label for="stageProfileFile"><spring:message code="label.admin.uploadProfile" />:
						(940x350px) <spring:message code="label.admin.bannerInfo" /> 879x195px</label>
					<c:if test="${not empty stage.stageProfileImage}">
						<img src="${hostname}${stage.stageProfileImage}" class="img-rounded" />
						<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/profileimage/delete"><i
							class="icon-trash pull-right"></i></a>
					</c:if>
					<div id="fileuploadbutton2" class="btn" tabindex="10">
						<spring:message code="label.admin.chooseImg" />
					</div>
					<br /> <input type="file" accept="image/*" name="stageProfileFile" class="fileupload2" />
				</div>
			</div>
		</form:form>
	</div>
	<hr>
	<div class="row-fluid">
		<div class="span6">
			<c:choose>
				<c:when test="${not empty stage.devices}">
					<h3>
						<spring:message code="label.admin.assignedDevices" />
					</h3>
					<ul>
						<c:forEach items="${stage.devices}" var="device">
							<li>${device.username} (<spring:message code="label.admin.devices" /> ID:
								${device.deviceId}) <a class="badge badge-important"
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/device/${device.deviceId}/remove"
								target="_self"><spring:message code="label.admin.removeDevice" /></a></li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h4>
						<spring:message code="label.admin.noDevices" />
					</h4>
				</c:otherwise>
			</c:choose>

			<button id="adder" type="submit" class="btn btn-primary" tabindex="12">
				<spring:message code="label.admin.assignNewDevice" />
			</button>
			<br />
			<div class="newItem row-fluid">
				<c:choose>
					<c:when test="${not empty devices}">
						<form:form method="post" name="device"
							action="/admin/race/${race.raceId}/stage/${stage.stageId}/device/add">
							<div class="control-group">
								<div class="controls">
									<c:forEach items="${devices}" var="device">
										<label class="checkbox"> <input type="checkbox" name="device"
											value="${device.deviceId}"> ${device.username} (<spring:message
												code="label.admin.devices" /> ID: ${device.deviceId })
										</label>
									</c:forEach>
									<button type="submit" class="btn" tabindex="13">Speichern</button>
									 <br /> <br />
									<p class="alert alert-info">
										<spring:message code="label.admin.assignToAllStages" />
									</p>
								</div>
							</div>
						</form:form>
					</c:when>
					<c:otherwise>
						<h4>
							<spring:message code="label.admin.noKnownDevices" />
						</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<hr>
	<div class="row-fluid">
		<div class="span6">
			<h3>
				<spring:message code="label.admin.marchtable" />
			</h3>
			<p>
				<spring:message code="label.admin.csvWithCol" />
				:
			</p>
			<pre>icon {info, achtung, bahn, tunnel, verpflegung, rechts, links, bodenwelle} , altitude, distance, distanceToGo, settlement, advertisingColumn, raceSlow, raceMedium, raceFast</pre>
			<form:form method="post" name="marchtableimport"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/marchtable/import"
				enctype="multipart/form-data">
				<div>
					<label for="marchTableCsv"><spring:message code="label.admin.uploadMarchtable" />:</label>
					<div id="fileuploadbutton4" class="btn" tabindex="14">
						<spring:message code="label.admin.chooseCsv" />
					</div>
					<br /> <input type="file" accept=".csv" name="marchTableCsv" class="fileupload4" />
				</div>
				<button type="submit" class="btn btn-primary" tabindex="15">
					<spring:message code="label.admin.import" />
				</button>
			</form:form>
			<c:if test="${not empty marchTable}">
				<p>
					<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/marchtable/delete/all"
						class="badge badge-important pull-right"><spring:message
							code="label.admin.deleteAll" /></a>
				</p>
				<table class="table hover-table">
					<c:forEach items="${marchTable}" var="mti">
						<tr>
							<td>${mti.settlement}</td>
							<td>${mti.altitude} <spring:message code="label.admin.altitude" /></td>
							<td>${mti.distance} km</td>
							<td>${mti.raceMedium} &Oslash;</td>
							<td><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/marchtable/delete/${mti.marchTableItemId}"><i
									class="icon-trash pull-right"></i></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<hr>

	<div class="row-fluid">
		<div class="span6">
			<h3>
				<spring:message code="label.admin.importRider" />
			</h3>
			<p>
				<spring:message code="label.admin.csvWithCol" />
				:
			</p>
			<pre>"startNr", "name", "team", "teamshort"</pre>
			<form:form method="post" name="fahrerimport"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/rider/import"
				enctype="multipart/form-data">
				<div>
					<label for="riderCsv"><spring:message code="label.admin.uploadRider" />:</label>
					<div id="fileuploadbutton3" class="btn" tabindex="16">
						<spring:message code="label.admin.chooseCsv" />
					</div>
					<br /> <input type="file" accept=".csv" name="riderCsv" class="fileupload3" />
				</div>
				<button type="submit" class="btn btn-primary" tabindex="17">
					<spring:message code="label.admin.import" />
				</button>

			</form:form>
			<c:if test="${not empty riders}">
				<p>
					<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/rider/delete/all"
						class="badge badge-important pull-right"><spring:message
							code="label.admin.deleteAll" /></a>
				</p>
				<table class="table hover-table">
					<c:forEach items="${riders}" var="rider">
						<tr>
							<td>${rider.startNr}</td>
							<td>${rider.name}</td>
							<td>${rider.teamShort}</td>
							<td><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/rider/delete/${rider.riderId}"><i
									class="icon-trash pull-right"></i></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<!-- DateTime Picker -->
	<script type="text/javascript">
		// datetimepicker
		// http://tarruda.github.io/bootstrap-datetimepicker/
		$.fn.datetimepicker.defaults = {
			language : 'de-CH',
			maskInput : true, // disables the text input mask
			pickDate : true, // disables the date picker
			pickTime : true, // disables de time picker
			pick12HourFormat : false, // enables the 12-hour format time picker
			pickSeconds : false, // disables seconds in the time picker
			startDate : -Infinity, // set a minimum date
			endDate : Infinity
		};
		$(function() {
			$('#datetimepicker1').datetimepicker({
			});
			$('#datetimepicker2').datetimepicker({
			});
			$('#datetimepicker3').datetimepicker({
				pickDate : false,
				pickSeconds : true
			});
		});
	</script>

	<!-- Slug Generator -->
	<script type="text/javascript">
		// Slug generator
		$('.toSlug').keyup(function() {
			$.ajax({
				type : "POST",
				url : "/utils/slug",
				data : {
					toSlug : $('.toSlug').val()
				},
				success : function(data) {
					$('.theSlug').val(data);
				}
			});
		});
		$('.toRaceSlug').keyup(function() {
			$.ajax({
				type : "POST",
				url : "/utils/slug",
				data : {
					toSlug : $('.toRaceSlug').val()
				},
				success : function(data) {
					$('.theRaceSlug').val(data);
				}
			});
		});
	</script>

	<!-- Admin Scripts -->
	<script type="text/javascript">
		// UX
		$("#adder").click(function(event) {
			event.stopPropagation();
		});
		$("#adder").click(function() {
			$(".newItem").slideToggle('fast');
		});
		$('#fileuploadbutton').bind("click", function() {
			$('.fileupload').click();
		});
		$('#fileuploadbutton2').bind("click", function() {
			$('.fileupload2').click();
		});
		$('#fileuploadbutton3').bind("click", function() {
			$('.fileupload3').click();
		});
		$('#fileuploadbutton4').bind("click", function() {
			$('.fileupload4').click();
		});

		// color picker
		$('.colorpicker').colorpicker();
	</script>
</body>
</html>