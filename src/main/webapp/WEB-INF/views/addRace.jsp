<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<body>
	<div class="row-fluid">
		<div class="span12">
			<form:form method="post" name="race" action="/admin/race/add">
				<label for="raceName">Rennname</label> <input type="text"
					name="raceName" /> <label for="description">Beschreibung</label>
				<textarea name="description" row="3"></textarea>
				<label for="year">Jahr</label><input type="number" name="year"/>
				<br><input type="submit" value="Hinzufügen"/>
			</form:form>
		</div>
	</div>
</body>
</html>