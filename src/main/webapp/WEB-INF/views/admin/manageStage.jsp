<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<form:form method="post" name="stage" action="/admin/stage/add">
			<label for="stageName">Etappenname</label>
			<input type="text" name="stageName" />
			<label for="description">Beschreibung</label>
			<input type="text" name="stageDescription" />
			<label for="distance">Distanz</label>
			<input type="number" name="distance" />
			<br>
			<input type="submit" value="Hinzufügen" />
		</form:form>
	</div>
</body>
</html>