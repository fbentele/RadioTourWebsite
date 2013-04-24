<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<div class="row-fluid">
			<h2>Rennen bearbeiten:</h2>
			<p>
				<form:form method="post" name="race" action="/admin/race/edit/${race.raceId}">
					<div class="span3">
						<input type="hidden" name="raceId" value="${race.raceId}" /> <label for="raceName">Rennname</label>
						<input type="text" name="raceName" class="toRaceSlug" value="${race.raceName}"
							tabindex="1" /><label for="raceSlug">Kurzname</label> <input type="text"
							name="raceSlug" class="theRaceSlug" value="${race.raceSlug}" tabindex="1" /> <label
							for="year">Jahr</label> <input type="number" name="year" value="${race.year}"
							tabindex="2" /> <label for="visible"><input type="checkbox" name="visible"
							value="true" <c:if test="${race.visible == true}">checked</c:if>> Sichtbar</label> <br>
						<button type="submit" class="btn btn-primary">Speichern</button>
					</div>
					<div class="span4">
						<label for="raceDescription">Beschreibung</label>
						<textarea name="raceDescription" rows="6" class="span12" tabindex="3">${race.raceDescription}</textarea>
					</div>
				</form:form>
			</p>
		</div>
		<div class="row-fluid">
			<c:choose>
				<c:when test="${not empty stages}">
					<h3>Etappen für dieses Rennen</h3>
					<table class="table table-hover">
						<tr>
							<th>Etappe</th>
							<th>Beschreibung</th>
							<th>Von</th>
							<th>Bis</th>
							<th>Distanz</th>
							<th>Sichtbar</th>
							<th>Bearbeiten</th>
							<th>Löschen</th>
						</tr>
						<c:forEach items="${stages}" var="stage">
							<tr>
								<td>${stage.stageName}</td>
								<td>${stage.stageDescription}</td>
								<td>${stage.starttime}</td>
								<td>${stage.endtime}</td>
								<td>${stage.distance}</td>
								<td>${stage.visible}</td>
								<td><a href="/admin/race/${race.raceId}/stage/edit/${stage.stageId}">Bearbeiten</a></td>
								<td><a href="/admin/race/${race.raceId}/stage/delete/${stage.stageId}">Löschen</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h4>Keine Etappe definiert, bitte neue Etappe erstellen.</h4>
				</c:otherwise>
			</c:choose>
		</div>
		<button id="adder" type="submit" class="btn btn-primary">Neue Etappe</button>
		<div class="newItem row-fluid">
			<form method="post" name="stage" action="/admin/race/${race.raceId}/stage/add"
				enctype="multipart/form-data">
				<div class="span4">
					<label for="stageName">Etappenname</label> <input type="text" name="stageName"
						class="toSlug" tabindex="4"> <label for="stageSlug">Etappenkurzname</label> <input
						type="text" name="stageSlug" class="theSlug" tabindex="5"> <label
						for="distance">Distanz</label>
					<div class="input-append">
						<input type="number" name="distance" tabindex="8"> <span class="add-on">km</span>
					</div>
					<label for="visible"> <input type="checkbox" name="visible" value="true">
						Sichtbar
					</label>
					<div>
						<label for="bannerImageFile">Etappen Banner Bild hochladen:</label> <img
							src="http://www.placehold.it/300x50/EFEFEF/AAAAAA&text=kein+Bild" class="img-rounded">
						<div id="fileuploadbutton" class="btn">Bild auswählen</div>
						<br /> <input type="file" accept="image/*" name="bannerImageFile" class="fileupload" />
					</div>
					<div>
						<label for="stageProfileFile">Etappen (höhen) Profilbild hochladen:</label> <img
							src="http://www.placehold.it/300x50/EFEFEF/AAAAAA&text=kein+Bild" class="img-rounded">
						<div id="fileuploadbutton2" class="btn">Bild auswählen</div>
						<br /> <input type="file" accept="image/*" name="stageProfileFile"
							class="fileupload2" />
					</div>
					<br />
					<button type="submit" class="btn btn-primary" tabindex="10">Speichern</button>
				</div>
				<div class="span4">
					<label for="starttime">Startzeit</label>
					<div id="datetimepicker1" class="input-append date">
						<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="starttime" tabindex="6" />
						<span class="add-on"> <i data-time-icon="icon-time"
							data-date-icon="icon-calendar"></i></span>
					</div>
					<label for="endtime">Endzeit</label>
					<div id="datetimepicker2" class="input-append">
						<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="endtime" tabindex="7" />
						<span class="add-on"> <i data-time-icon="icon-time"
							data-date-icon="icon-calendar"></i></span>
					</div>

					<label for="stageDescription">Beschreibung</label>
					<textarea name="stageDescription" rows="6" class="span12" tabindex="8"></textarea>
				</div>
			</form>
		</div>
	</div>
</body>
</html>