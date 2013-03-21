<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>All Stages</title>
</head>
<body>

	<h2>Etappen</h2>
	<table class="table">
		<tr>
			<th>Timestamp</th>
			<th>Long</th>
			<th>Lat</th>
			<th>Alt</th>
			<th>Speed</th>
			<th>Direction</th>
			<th>Incline</th>
		</tr>

		<c:forEach items="${positions}" var="positionData">
			<tr>
				<td>${positionData.timestamp}</td>
				<td>${positionData.longitude}</td>
				<td>${positionData.latitude}</td>
				<td>${positionData.altitude}</td>
				<td>${positionData.speed}</td>
				<td>${positionData.direction}</td>
				<td>${positionData.incline}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>