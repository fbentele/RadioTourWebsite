<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="en">
<body>
	<h2>
		<spring:message code="label.admin.editDevice" />
	</h2>
	<div class="row-fluid">
		<div class="span4">
			<form:form method="post" name="device" action="/admin/device/edit/${device.deviceId}">
				<label for="deviceId"><spring:message code="label.admin.deviceId" /></label>
				<input type="text" name="deviceId" value="${device.deviceId}" readonly="readonly" />
				<label for="username"><spring:message code="label.admin.username" /></label>
				<input type="text" name="username" value="${device.username}" readonly="readonly" />
				<label for="phoneNr"><spring:message code="label.admin.phoneNr" /></label>
				<input type="text" name="phoneNr" value="${device.phoneNr}" readonly="readonly" />
				<label><spring:message code="label.admin.colorOnMap" /></label>
				<div class="input-append color colorpicker" data-color="${device.color}"
					data-color-format="hex">
					<input type="text" name="color" value="${device.color}" /> <span class="add-on"><i
						style="background-color: ${device.color}"></i></span>
				</div>
				<label><spring:message code="label.admin.devicePresentationName" /></label>
				<input type="text" name="labelName" value="${device.labelName}" />
				<hr>
				<h4><spring:message code="label.admin.driverInformation" /></h4>
				<label for="driverPhoneNr"><spring:message code="label.admin.driverPhoneNr" /></label>
				<input type="text" name="driverPhoneNr" value="${device.driverPhoneNr}" />
				<label for="driverName"><spring:message code="label.admin.driverName" /></label>
				<input type="text" name="driverName" value="${device.driverName}" />

				<button type="submit" class="btn btn-primary">
					<spring:message code="label.admin.save" />
				</button>
			</form:form>
		</div>
		<c:if test="${not empty stages}">
			<div class="span4">
				<spring:message code="label.admin.deviceInStage" />
				<ul>
					<c:forEach items="${stages}" var="stage">
						<li>${stage.stageName}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>