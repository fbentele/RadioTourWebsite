<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<h2>Rennen bearbeiten:</h2>
		<form:form method="post" name="race" action="/admin/race/edit/${race.raceId}">
			<input type="hidden" name="raceId" value="${race.raceId}" />
			<label for="raceName">Rennname</label>
			<input type="text" name="raceName" value="${race.raceName}" />
			<label for="description">Beschreibung</label>
			<textarea name="description">${race.description}</textarea>
			<label for="year">Jahr</label>
			<input type="number" name="year" value="${race.year}" />
			<br>
			<input type="submit" value="Bearbeiten" />
		</form:form>
	</div>
</body>
</html>