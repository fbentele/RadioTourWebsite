<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2>Etappe bearbeiten:</h2>
	<p>
		<a href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker">Liveticker
			starten</a>
	</p>
	<div class="row-fluid">
		<form:form method="post" name="stage"
			action="/admin/race/${race.raceId}/stage/edit/${stage.stageId}"
			enctype="multipart/form-data">
			<div class="span4">
				<input type="hidden" name="stageId" value="${stage.stageId}" /> <label for="stageName">Etappenname</label>
				<input type="text" name="stageName" class="toSlug" value="${stage.stageName}" /> <label
					for="stageSlug">Etappen Kurzname (slug)</label> <input type="text" name="stageSlug"
					class="theSlug" value="${stage.stageSlug}" />

				<div class="input-append">
					<label for="distance">Distanz</label> <input type="text" name="distance"
						value="${stage.distance}"> <span class="add-on">km</span>
				</div>
				<br> <label for="stageDescription">Beschreibung</label>
				<textarea name="stageDescription" rows="6" class="span12">${stage.stageDescription}</textarea>

				<button type="submit" class="btn btn-primary">Speichern</button>
			</div>
			<div class="span4">
				<label for="starttime">Startzeit</label>
				<div id="datetimepicker1" class="input-append date">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="starttime" tabindex="6"
						value="${stage.starttime}" /> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>
				<label for="endtime">Endzeit</label>
				<div id="datetimepicker2" class="input-append">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="endtime" tabindex="7"
						value="${stage.endtime}" /> <span class="add-on"> <i
						data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
				</div>

				<label for="visible"> <input type="checkbox" name="visible"
					value="true${stage.visible}" <c:if test="${stage.visible==true}">checked</c:if>>
					Öffentlich sichtbar
				</label>
				<div>
					<label for="adCode">HTML Code für Werbeelement (300px X 200px)</label>
					<textarea name="adCode">${stage.adCode}</textarea>
				</div>

				<div>
					<label for="bannerImageFile">Etappen Banner Bild hochladen: (940x210px)</label> <img
						src="${bannerImage}" class="img-rounded">
					<div id="fileuploadbutton" class="btn">Bild auswählen</div>
					<br /> <input type="file" accept="image/*" name="bannerImageFile" class="fileupload" />
				</div>
				<div>
					<label for="stageProfileFile">Strecken Profil Bild hochladen: (940x210px)</label> <img
						src="${stageProfileImage}" class="img-rounded">
					<div id="fileuploadbutton2" class="btn">Bild auswählen</div>
					<br /> <input type="file" accept="image/*" name="stageProfileFile" class="fileupload2" />
				</div>
			</div>
		</form:form>
	</div>

	<div class="row-fluid">
		<div class="span6">
			<c:choose>
				<c:when test="${not empty stage.devices}">
					<h3>Zugeordnete Geräte</h3>
					<ul>
						<c:forEach items="${stage.devices}" var="device">
							<li>${device.username} (Geräte ID: ${device.deviceId}) <a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/device/${device.deviceId}/remove"
								target="_self">Entfernen</a></li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h4>Dieser Etappe sind keine Geräte zugeordnet, bitte neue Geräte zuordnen.</h4>
				</c:otherwise>
			</c:choose>

			<button id="adder" type="submit" class="btn btn-primary">Neues Gerät zuordnen</button>
			<div class="newItem row-fluid">
				<c:choose>
					<c:when test="${not empty devices}">
						<form:form method="post" name="device"
							action="/admin/race/${race.raceId}/stage/${stage.stageId}/device/add">
							<div class="control-group">
								<div class="controls">
									<c:forEach items="${devices}" var="device">
										<label class="checkbox"> <input type="checkbox" name="device"
											value="${device.deviceId}"> ${device.username} (Geräte ID:
											${device.deviceId })
										</label>
									</c:forEach>
									<button type="submit" class="btn">Speichern</button>
								</div>
							</div>
						</form:form>
					</c:when>
					<c:otherwise>
						<h4>Es sind keine Geräte bekannt</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="span6">
			<h3>Fahrer importieren</h3>
			<p>CSV Datei mit den Spalten:</p>
			<pre>"startNr", "name", "birthday yyyyMMdd", "team", "teamshort"</pre>
			<form:form method="post" name="fahrerimport"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/rider/import"
				enctype="multipart/form-data">
				<div>
					<label for="riderCsv">Fahrerliste hochladen:</label>
					<div id="fileuploadbutton3" class="btn">CSV Datei auswählen</div>
					<br /> <input type="file" accept=".csv" name="riderCsv" class="fileupload3" />
				</div>
				<button type="submit" class="btn btn-primary">Importieren</button>

			</form:form>
			<c:if test="${not empty riders}">
				<table class="table hover-table">
					<c:forEach items="${riders}" var="rider"><tr><td>${rider.startNr}</td><td>${rider.name}</td><td>Bearbeiten</td><td>Löschen</td></tr></c:forEach>

				</table>

			</c:if>
		</div>
	</div>
</body>
</html>