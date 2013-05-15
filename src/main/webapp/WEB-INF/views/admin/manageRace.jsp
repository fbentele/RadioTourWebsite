<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2>
		<spring:message code="label.admin.allRaces" />
	</h2>
	<table class="table table-hover" data-provides="rowlink">
		<tr>
			<th>ID</th>
			<th><spring:message code="label.admin.raceName" /></th>
			<th><spring:message code="label.admin.year" /></th>
			<th><spring:message code="label.admin.description" /></th>
			<th><spring:message code="label.admin.edit" /></th>
			<th><spring:message code="label.admin.delete" /></th>
		</tr>
		<c:forEach items="${races}" var="race">
			<tr>
				<td><a href="/admin/race/edit/${race.raceId}">${race.raceId}</a></td>
				<td>${race.raceName}</td>
				<td>${race.year}</td>
				<td><c:choose>
						<c:when test="${fn:length(race.raceDescription) > 120}">
									${fn:substring(race.raceDescription, 0, 120)}...
						</c:when>
						<c:otherwise>
							${race.raceDescription}
						</c:otherwise>
					</c:choose></td>
				<td><a href="/admin/race/edit/${race.raceId}" target="_self"><spring:message
							code="label.admin.edit" /></a></td>
				<td><a href="/admin/race/delete/${race.raceId}" target="_self"><spring:message
							code="label.admin.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<button id="adder" type="submit" class="btn btn-primary">
			<spring:message code="label.admin.newRace" />
		</button>
	</p>
	<div <c:if test="${empty showhidden}">class="newItem"</c:if>>
		<form:form method="post" name="race" action="/admin/race/add" commandName="race">
			<div class="span3">
				<label for="raceName"><spring:message code="label.admin.raceName" /></label>
				<form:errors path="raceName" cssClass="formerror" />
				<form:input path="raceName" class="toRaceSlug" type="text" name="raceName" tabindex="1" />
				<label for="raceSlug"><spring:message code="label.admin.raceSlug" /> (Slug)</label>
				<form:errors path="raceSlug" cssClass="formerror" />
				<form:input path="raceSlug" type="text" name="raceSlug" class="theRaceSlug" tabindex="1" />
				<label for="year"><spring:message code="label.admin.year" /></label>
				<form:errors path="year" cssClass="formerror" />
				<form:input path="year" type="number" name="year" tabindex="2" />
				<label for="visible"> <input type="checkbox" name="visible" value="true">
				<spring:message code="label.admin.visible" /></label>
				<form:errors path="visible" cssClass="formerror" />
				<input type="submit" value="Hinzufügen" tabindex="4" />
			</div>
			<div class="span4">
				<label for="raceDescription"><spring:message code="label.admin.description" /></label>
				<form:textarea path="raceDescription" name="raceDescription" cols="50" rows="5"
					tabindex="3"></form:textarea>
			</div>
			<br>
		</form:form>
	</div>
</body>
</html>