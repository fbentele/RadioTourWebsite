<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>All Stages</title>
</head>
<body>
	<h2>Rennen</h2>
	<table class="table table-hover">
		<tr>
			<th>Name:</th>
			<th>Beschreibung</th>
		</tr>
		<c:forEach items="${races}" var="race">
			<tr>
				<td>${race.raceName}</td>
				<td>${race.description}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>