<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<div class="span10">
		<h2>Aufnahmeger�te:</h2>
		<table class="table table-hover">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Anz. Rennen</th>
				<th>Anz Pos</th>
				<th>L�schen</th>
			</tr>
			<c:forEach items="${devices}" var="device">
				<tr>
					<td>${device.deviceId}</td>
					<td>${device.username}</td>
					<td>a</td>
					<td>d</td>
					<td><a href="/admin/device/delete/${device.deviceId}" target="_self">L�schen</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>