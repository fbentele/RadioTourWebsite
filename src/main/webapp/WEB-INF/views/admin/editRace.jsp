<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="row-fluid">
		<h2>
			<spring:message code="label.admin.editRace" />
		</h2>
		<p>
			<form:form method="post" name="race" action="/admin/race/edit/${race.raceId}">
				<div class="span3">
					<input type="hidden" name="raceId" value="${race.raceId}" /> <label for="raceName"><spring:message
							code="label.admin.raceName" /></label>
					<form:errors path="raceName" cssClass="formerror" />
					<input type="text" name="raceName" class="toRaceSlug" value="${race.raceName}"
						tabindex="1" /> <label for="raceSlug"><spring:message
							code="label.admin.raceSlug" /></label>
					<form:errors path="raceSlug" cssClass="formerror" />
					<input type="text" name="raceSlug" class="theRaceSlug" value="${race.raceSlug}"
						tabindex="2" /> <label for="year"><spring:message code="label.admin.year" /></label>
					<form:errors path="year" cssClass="formerror" />
					<input type="number" name="year" value="${race.year}" tabindex="3" /> <label
						for="visible"><input type="checkbox" name="visible" value="true"
						<c:if test="${race.visible == true}">checked</c:if> tabindex="4" /> <spring:message
							code="label.admin.visible" /></label> <br>
					<button type="submit" class="btn btn-primary" tabindex="6">
						<spring:message code="label.admin.save" />
					</button>
				</div>
				<div class="span9">
					<label for="raceDescription"><spring:message code="label.admin.description" /></label>
					<textarea name="raceDescription" rows="8" class="span12" tabindex="5">${race.raceDescription}</textarea>
				</div>
			</form:form>
		</p>
	</div>
	<div class="row-fluid">
		<c:choose>
			<c:when test="${not empty stages}">
				<h3>
					<spring:message code="label.admin.stagesForRace" />
				</h3>
				<table class="table table-hover" data-provides="rowlink">
					<tr>
						<th><spring:message code="label.stage" /></th>
						<th><spring:message code="label.description" /></th>
						<th><spring:message code="label.admin.from" /></th>
						<th><spring:message code="label.admin.to" /></th>
						<th><spring:message code="label.admin.distance" /></th>
						<th><spring:message code="label.admin.visible" /></th>
						<th></th>
					</tr>
					<c:forEach items="${stages}" var="stage">
						<tr>
							<td><a href="/admin/race/${race.raceId}/stage/edit/${stage.stageId}">${stage.stageName}</a></td>
							<td>
							<c:choose>
								<c:when test="${fn:length(stage.stageDescription) > 120}">
									${fn:substring(stage.stageDescription, 0, 120)}...
								</c:when>
								<c:otherwise>
									${stage.stageDescription}
								</c:otherwise>
							</c:choose></td>
							<td>${stage.starttime}</td>
							<td>${stage.endtime}</td>
							<td>${stage.distance}</td>
							<td>${stage.visible}</td>
							<td class="nolink"><a href="#modal_delete${stage.stageId}" data-toggle="modal"><i class="icon-trash"></i></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<h4>
					<spring:message code="label.admin.noStage" />
				</h4>
			</c:otherwise>
		</c:choose>
	</div>
	<button id="adder" type="submit" class="btn btn-primary">
		<spring:message code="label.admin.newStage" />
	</button>
	<div <c:if test="${empty showhidden}">class="newItem"</c:if>>
		<form:form commandName="stage" method="post" name="stage"
			action="/admin/race/${race.raceId}/stage/add" enctype="multipart/form-data">
			<div class="span4">
				<label for="stageName"><spring:message code="label.admin.stageName" /></label>
				<form:errors path="stageName" cssClass="formerror" />
				<form:input path="stageName" type="text" name="stageName" class="toSlug" tabindex="10" />
				<label for="stageSlug"><spring:message code="label.admin.stageSlug" /></label>
				<form:errors path="stageSlug" cssClass="formerror" />
				<form:input path="stageSlug" type="text" name="stageSlug" class="theSlug" tabindex="11" />
				<label for="distance"><spring:message code="label.admin.distance" /></label>
				<div class="input-append">
					<form:input path="distance" type="text" name="distance" tabindex="12" />
					<span class="add-on">km</span>
				</div>
				<label for="visible"> <input type="checkbox" name="visible" value="true"
					tabindex="13" /> Sichtbar
				</label>
				<div>
					<label for="stageDescription"><spring:message code="label.admin.description" /></label>
					<textarea name="stageDescription" rows="6" tabindex="14"></textarea>
				</div>
				<br />
				<button type="submit" class="btn btn-primary" tabindex="20">Speichern</button>
			</div>
			<div class="span4">
				<label for="starttime"><spring:message code="label.admin.starttime" /></label>
				<div id="datetimepicker1" class="input-append date">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="starttime" tabindex="15" />
					<span class="add-on"> <i data-time-icon="icon-time"
						data-date-icon="icon-calendar"></i></span>
				</div>
				<label for="endtime"><spring:message code="label.admin.endtime" /></label>
				<div id="datetimepicker2" class="input-append">
					<input data-format="dd.MM.yyyy - hh:mm" type="datetime" name="endtime" tabindex="16" />
					<span class="add-on"> <i data-time-icon="icon-time"
						data-date-icon="icon-calendar"></i></span>
				</div>
				<div>
					<label for="bannerImageFile"><spring:message code="label.admin.uploadBanner" />:</label>
					<img src="http://www.placehold.it/300x50/EFEFEF/AAAAAA&text=kein+Bild"
						class="img-rounded">
					<div id="fileuploadbutton" class="btn" tabindex="17">
						<spring:message code="label.admin.chooseImg" />
					</div>
					<br /> <input type="file" accept="image/*" name="bannerImageFile" class="fileupload" /><br />
				</div>
				<div>
					<label for="stageProfileFile"><spring:message code="label.admin.uploadProfile" />:</label>
					<img src="http://www.placehold.it/300x50/EFEFEF/AAAAAA&text=kein+Bild"
						class="img-rounded">
					<div id="fileuploadbutton2" class="btn" tabindex="18">
						<spring:message code="label.admin.chooseImg" />
					</div>
					<br /> <input type="file" accept="image/*" name="stageProfileFile" class="fileupload2" /><br />
				</div>
				<label for="adCode"><spring:message code="label.admin.htmlForAd" /></label>
				<textarea name="adCode" rows="6" tabindex="19"></textarea>
			</div>
		</form:form>
	</div>
	<c:forEach items="${stages}" var="stage">
		<div class="modal fade" id="modal_delete${stage.stageId}">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">&times;</a>
				<h3>
					<spring:message code="label.admin.deleteStage"></spring:message>
				</h3>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="label.admin.reallyDeleteStage" arguments="${stage.stageName}" />
				</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal"><spring:message code="label.admin.cancel" /></a> <a href="/admin/race/${race.raceId}/stage/delete/${stage.stageId}"
					class="btn btn-primary"><spring:message code="label.admin.delete" /></a>
			</div>
		</div>
	</c:forEach>
</body>
</html>