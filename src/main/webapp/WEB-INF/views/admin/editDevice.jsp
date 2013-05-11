<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<body>
	<h2><spring:message code="label.admin.editDevice" /></h2>
	<div class="row-fluid">
		<form:form method="post" name="device" action="/admin/device/edit/${device.deviceId}">
			<div class="span4">
				<input type="text" name="deviceId" value="${device.deviceId}" readonly="readonly"></input>
				<input type="text" name="username" value="${device.username}" readonly="readonly"></input>
				<label><spring:message code="label.admin.colorOnMap" />:</label>
				<div class="input-append color" data-color="rgb(255, 146, 180)" data-color-format="rgb">
					<input type="text" name="color" value="${device.color}" class="colorpicker"></input>
				</div>

				<label><spring:message code="label.admin.devicePresentationName" />: </label><input type="text" name="labelName"
					value="${device.labelName}" />

				<button type="submit" class="btn btn-primary"><spring:message code="label.admin.save" /></button>
			</div>
		</form:form>
	</div>


</body>
</html> 