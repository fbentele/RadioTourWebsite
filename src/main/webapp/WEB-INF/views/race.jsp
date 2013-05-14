<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>All Stages</title>
</head>
<body>
	<h2>Rennen</h2>
	<table class="table table-hover" data-provides="rowlink">
		<tr>
			<th>Name:</th>
			<th>Beschreibung</th>
		</tr>
		<c:forEach items="${races}" var="race">
			<tr>
				<td><a href="/race/${race.raceSlug}" >${race.raceName}</a></td>
				<td><c:choose>
						<c:when test="${fn:length(race.raceDescription) > 60}">
									${fn:substring(race.raceDescription, 0, 60)}...
						</c:when>
						<c:otherwise>
							${race.raceDescription}
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>