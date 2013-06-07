<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tourlive - ${race.raceName}</title>
</head>
<body>
	<div class="row-fluid">
		<div class="visible-phone well">
			<spring:message code="label.stages" />
			<c:forEach items="${stages}" var="stage">
				<li><a href="/race/${race.raceSlug}/stage/${stage.stageSlug}">${stage.stageName}</a></li>
			</c:forEach>
		</div>
		<h2>${race.raceName}</h2>
		<p>${race.raceDescription}</p>
	</div>
</body>
</html>