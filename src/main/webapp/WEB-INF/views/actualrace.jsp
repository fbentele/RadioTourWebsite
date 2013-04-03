<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tourlive - ${race.raceName}</title>
</head>
<body>
	<div class="span10">
		<h2>${race.raceName}</h2>
		<p>${race.raceDescription}</p>
	</div>
</body>
</html>