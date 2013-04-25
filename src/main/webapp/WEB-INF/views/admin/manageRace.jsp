<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
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
					<td>${race.raceDescription}</td>
					<td><a href="/admin/race/edit/${race.raceId}" target="_self">Bearbeiten</a></td>
					<td><a href="/admin/race/delete/${race.raceId}" target="_self">Löschen</a></td>
				</tr>
			</c:forEach>
		</table>
		<p><button id="adder" type="submit" class="btn btn-primary">Neues Rennen</button></p>
		<div class="newItem">

			<form:form method="post" name="race" action="/admin/race/add">
				<div class="span3">
					<label for="raceName">Rennname</label> <input class="toRaceSlug" type="text" name="raceName" tabindex="1"/><label for="raceSlug">Kurzname für URL (Slug)</label>
						<input type="text" name="raceSlug" class="theRaceSlug" tabindex="1" />  <label
						for="year">Jahr</label> <input type="number" name="year" tabindex="2"/><label for="visible"><input type="checkbox" name="visible" value="true"> Sichtbar</label> <input type="submit"
						value="Hinzufügen" tabindex="4" />

				</div>
				<div class="span4">
					<label for="raceDescription">Beschreibung</label>
					<textarea name="raceDescription" cols="50" rows="5" tabindex="3"></textarea>
				</div>
				<br>
			</form:form>

		</div>
</body>
</html>