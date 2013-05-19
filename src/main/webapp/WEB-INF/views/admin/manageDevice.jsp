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
				<th><spring:message code="label.admin.username"/></th>
				<th><spring:message code="label.admin.labelName"/></th>
				<th><spring:message code="label.admin.phoneNr"/></th>
				<th><spring:message code="label.admin.colorOnMap"/></th>
				<th></th>
			</tr>
			<c:forEach items="${devices}" var="device">
				<tr>
					<td><a href="/admin/device/edit/${device.deviceId}" target="_self">${device.deviceId}</a></td>
					<td>${device.username}</td>
					<td>${device.labelName}</td>
					<td>${device.phoneNr}</td>
					<td><span style="background-color: ${device.color}">&nbsp;&nbsp;</span></td>
					<td class="nolink"><a href="#modal_delete${device.deviceId}" data-toggle="modal" target="_self"><i class="icon-trash"></i></a></td>
				</tr>
			</c:forEach>
		</table>
		<c:forEach items="${devices}" var="device">
		<div class="modal fade" id="modal_delete${device.deviceId}">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">&times;</a>
				<h3>
					<spring:message code="label.admin.deleteDevice"></spring:message>
				</h3>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="label.admin.reallyDeleteDevice" arguments="${device.deviceId}" />
				</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal"><spring:message code="label.admin.cancel" /></a> <a href="/admin/device/delete/${device.deviceId}"
					class="btn btn-primary"><spring:message code="label.admin.delete" /></a>
			</div>
		</div>
	</c:forEach>
</body>
</html>