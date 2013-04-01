<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<h2>Rennen bearbeiten:</h2>
		<form:form method="post" name="race"
			action="/admin/race/edit/${race.raceId}">
			<input type="hidden" name="raceId" value="${race.raceId}" />
			<label for="raceName">Rennname</label>
			<input type="text" name="raceName" value="${race.raceName}" />
			<label for="raceDescription">Beschreibung</label>
			<textarea name="raceDescription">${race.raceDescription}</textarea>
			<label for="year">Jahr</label>
			<input type="number" name="year" value="${race.year}" />
			<br>
			<button type="submit" class="btn btn-primary">Speichern</button>
		</form:form>
		<h3>Etappen für dieses Rennen</h3>
		<table class="table table-hover">
			<tr>
				<th>Etappe</th>
				<th>Beschreibung</th>
				<th>Von</th>
				<th>Bis</th>
				<th>Distanz</th>
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
					<td><a href="/admin/race/${race.raceId}/stage/edit/${stage.stageId}">Bearbeiten</a></td>
					<td><a href="/admin/race/${race.raceId}/stage/delete/${stage.stageId}">Löschen</a></td>
				</tr>
			</c:forEach>
		</table>
		<button id="adder" type="submit" class="btn btn-primary">Neue
			Etappe</button>
		<div class="newItem">
			<form:form method="post" name="stage"
				action="/admin/race/${race.raceId}/stage/add">
				<label for="stageName">Etappenname</label>
				<input type="text" name="stageName">
				<label for="stageDescription">Beschreibung</label>
				<textarea name="stageDescription"></textarea>
				<label for="starttime">Startzeit</label>
				<input type="datetime" name="starttime" />
				<label for="endtime">Startzeit</label>
				<input type="datetime" name="endtime" />
				<label for="distance">Distanz</label>
				<div class="input-append">
					<input type="number" name="distance"> <span class="add-on">km</span>
				</div>
				<br />
				<button type="submit" class="btn btn-primary">Speichern</button>
			</form:form>
		</div>
	</div>
</body>
</html>