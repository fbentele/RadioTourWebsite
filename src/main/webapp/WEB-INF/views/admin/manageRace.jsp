<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<h2>Alle Rennen:</h2>
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Jahr</th>
				<th>Beschreibung</th>
				<th>Bearbeiten</th>
				<th>Löschen</th>
			</tr>
			<c:forEach items="${races}" var="race">
				<tr>
					<td>${race.raceId}</td>
					<td>${race.raceName}</td>
					<td>${race.year}</td>
					<td>${race.description}</td>
					<td><a href="/admin/race/edit/${race.raceId}" target="_self">Bearbeiten</a></td>
					<td><a href="/admin/race/delete/${race.raceId}" target="_self">Löschen</a></td>
				</tr>
			</c:forEach>
		</table>
		<h2>Neues Rennen hinzufügen</h2>
		<form:form method="post" name="race" action="/admin/race/add">
			<label for="raceName">Rennname</label>
			<input type="text" name="raceName" />
			<label for="description">Beschreibung</label>
			<textarea name="description"></textarea>
			<label for="year">Jahr</label>
			<input type="number" name="year" />
			<br>
			<input type="submit" value="Hinzufügen" />
		</form:form>
	</div>
</body>
</html>