<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<h2>Etappe bearbeiten:</h2>
		<form:form method="post" name="stage"
			action="/admin/race/${race.raceId}/stage/edit/${stage.stageId}">
			<input type="hidden" name="stageId" value="${stage.stageId}" />

			<label for="stageName">Etappenname</label>
			<input type="text" name="stageName" value="${stage.stageName}" />

			<label for="stageDescription">Beschreibung</label>
			<textarea name="stageDescription">${stage.stageDescription}</textarea>

			<label for="starttime">Startzeit</label>
			<input type="datetime" name="starttime" value="${stage.starttime}">
			<label for="endtime">Rennende</label>
			<input type="datetime" name="Endtime" value="${stage.endtime}">

			<label for="distance">Distanz</label>
			<div class="input-append">
				<input type="number" name="distance" value="${stage.distance}"><span
					class="add-on">km</span>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Speichern</button>
		</form:form>
		<h3>Zugeordnete Geräte</h3>
		<c:forEach items="${stage.devices}" var="device">
			${device.username} <br/>
		</c:forEach>
	</div>
</body>
</html>