<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
		<h2>Aufnahmegeräte:</h2>
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Anz. Rennen</th>
				<th>Anz Pos</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${devices}" var="device">
				<tr>
					<td>${device.deviceId}</td>
					<td>${device.username}</td>
					<td>1</td>
					<td>1</td>
					<td><a href="/admin/device/edit/${device.deviceId}" target="_self">Bearbeiten</a></td>
					<td><a href="/admin/device/delete/${device.deviceId}" target="_self">Löschen</a></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>