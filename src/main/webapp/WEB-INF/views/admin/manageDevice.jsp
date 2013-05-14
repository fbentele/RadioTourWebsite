<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<body>
		<h2><spring:message code="label.admin.device" />:</h2>
		<table class="table table-hover" data-provides="rowlink">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${devices}" var="device">
				<tr>
					<td><a href="/admin/device/edit/${device.deviceId}" target="_self">${device.deviceId}</a></td>
					<td>${device.username}</td>
					<td><a href="/admin/device/delete/${device.deviceId}" target="_self"><spring:message code="label.admin.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>