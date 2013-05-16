<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2><spring:message code="label.admin.editStage" />:</h2>
	<p>
		<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker"><spring:message code="label.admin.startLiveticker" /></a>
	</p>
	<div class="row-fluid">
		<form:form method="post" name="stage"
			action="/admin/race/${race.raceId}/stage/edit/${stage.stageId}"
			enctype="multipart/form-data">
			<div class="span4">
				<input type="hidden" name="stageId" value="${stage.stageId}" /> <label for="stageName"><spring:message code="label.admin.stageName" /></label>
				<input type="text" name="stageName" class="toSlug" value="${stage.stageName}" tabindex="1" /> <label
					for="stageSlug"><spring:message code="label.admin.stageSlug" /></label> <input type="text" name="stageSlug"
					class="theSlug" value="${stage.stageSlug}" tabindex="2" />

				<div class="input-append">
					<label for="distance"><spring:message code="label.admin.distance" /></label> <input type="text" name="distance"
						value="${stage.distance}" tabindex="3"> <span class="add-on">km</span>
				</div>
				<br> <label for="stageDescription"><spring:message code="label.description" /></label>
				<textarea name="stageDescription" rows="6" class="span12" tabindex="4">${stage.stageDescription}</textarea>

				<button type="submit" class="btn btn-primary" tabindex="11"><spring:message code="label.admin.save" /></button>
			</div>
			<div class="span4">
				<label for="starttime"><spring:message code="label.admin.starttime" /></label>
				<div id="datetimepicker1" class="input-append date">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="starttime" 
						value="${stage.starttime}" tabindex="5"/> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>
				<label for="endtime"><spring:message code="label.admin.endtime" /></label>
				<div id="datetimepicker2" class="input-append">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="endtime" 
						value="${stage.endtime}" tabindex="6" /> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>

				<label for="visible"> <input type="checkbox" name="visible"
					value="true${stage.visible}" <c:if test="${stage.visible==true}">checked</c:if> tabindex="7">
					<spring:message code="label.admin.visible" />
				</label>
				<div>
					<label for="adCode"><spring:message code="label.admin.htmlForAd" /> (300px X 200px)</label>
					<textarea name="adCode" tabindex="8">${stage.adCode}</textarea>
				</div>

				<div>
					<label for="bannerImageFile"><spring:message code="label.admin.uploadBanner" />: (940x210px)</label>
					<c:if test="${not empty stage.bannerImage}">
						<img src="${hostname}${stage.bannerImage}" class="img-rounded" />
						<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/bannerimage/delete"><i class="icon-trash pull-right"></i></a>
					</c:if>
					<div id="fileuploadbutton" class="btn" tabindex="9"><spring:message code="label.admin.chooseImg" /></div>
					<br /> <input type="file" accept="image/*" name="bannerImageFile" class="fileupload" />
				</div>
				<div>
					<label for="stageProfileFile"><spring:message code="label.admin.uploadProfile" />: (940x350px)</label>
					<c:if test="${not empty stage.stageProfileImage}">
						<img src="${hostname}${stage.stageProfileImage}" class="img-rounded" />
						<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/profileimage/delete"><i class="icon-trash pull-right"></i></a>
					</c:if>
					<div id="fileuploadbutton2" class="btn" tabindex="10"><spring:message code="label.admin.chooseImg" /></div>
					<br /> <input type="file" accept="image/*" name="stageProfileFile" class="fileupload2" />
				</div>
			</div>
		</form:form>
	</div>

	<div class="row-fluid">
		<div class="span6">
			<c:choose>
				<c:when test="${not empty stage.devices}">
					<h3><spring:message code="label.admin.assignedDevices" /></h3>
					<ul>
						<c:forEach items="${stage.devices}" var="device">
							<li>${device.username} (<spring:message code="label.admin.devices" /> ID: ${device.deviceId}) <a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/device/${device.deviceId}/remove"
								target="_self"><spring:message code="label.admin.delete" /></a></li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h4><spring:message code="label.admin.noDevices" /></h4>
				</c:otherwise>
			</c:choose>

			<button id="adder" type="submit" class="btn btn-primary" tabindex="12"><spring:message code="label.admin.assignNewDevice" /></button>
			<div class="newItem row-fluid">
				<c:choose>
					<c:when test="${not empty devices}">
						<form:form method="post" name="device"
							action="/admin/race/${race.raceId}/stage/${stage.stageId}/device/add">
							<div class="control-group">
								<div class="controls">
									<c:forEach items="${devices}" var="device">
										<label class="checkbox"> <input type="checkbox" name="device"
											value="${device.deviceId}"> ${device.username} (<spring:message code="label.admin.devices" /> ID:
											${device.deviceId })
										</label>
									</c:forEach>
									<button type="submit" class="btn" tabindex="13">Speichern</button>
								</div>
							</div>
						</form:form>
					</c:when>
					<c:otherwise>
						<h4><spring:message code="label.admin.noKnownDevices" /></h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<h3><spring:message code="label.admin.marchtable" /></h3>
			<p><spring:message code="label.admin.csvWithCol" />:</p>
			<pre>icon , altitude, distance, distanceToGo, settlement, advertisingColumn, raceSlow, raceMedium, raceFast</pre>
			<form:form method="post" name="marchtableimport"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/marchtable/import"
				enctype="multipart/form-data">
				<div>
					<label for="marchTableCsv"><spring:message code="label.admin.uploadMarchtable" />:</label>
					<div id="fileuploadbutton4" class="btn" tabindex="14"><spring:message code="label.admin.chooseCsv" /></div>
					<br /> <input type="file" accept=".csv" name="marchTableCsv" class="fileupload4" />
				</div>
				<button type="submit" class="btn btn-primary" tabindex="15"><spring:message code="label.admin.import" /></button>
			</form:form>
			<c:if test="${not empty marchTable}">
				<table class="table hover-table">
					<c:forEach items="${marchTable}" var="mti">
						<tr>
							<td>${mti.settlement}</td>
							<td>${mti.distance}</td>
							<td><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/marchtable/delete/${mti.marchTableItemId}"><i
									class="icon-trash pull-right"></i></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<h3><spring:message code="label.admin.importRider" /></h3>
			<p><spring:message code="label.admin.csvWithCol" />:</p>
			<pre>"startNr", "name", "birthday yyyyMMdd", "team", "teamshort"</pre>
			<form:form method="post" name="fahrerimport"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/rider/import"
				enctype="multipart/form-data">
				<div>
					<label for="riderCsv"><spring:message code="label.admin.uploadRider" />:</label>
					<div id="fileuploadbutton3" class="btn" tabindex="16"><spring:message code="label.admin.chooseCsv" /></div>
					<br /> <input type="file" accept=".csv" name="riderCsv" class="fileupload3" />
				</div>
				<button type="submit" class="btn btn-primary" tabindex="17"><spring:message code="label.admin.import" /></button>

			</form:form>
			<c:if test="${not empty riders}">
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
</body>
</html>