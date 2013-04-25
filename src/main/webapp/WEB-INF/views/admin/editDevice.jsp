<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<body>
	<h2>Gerät bearbeiten:</h2>
	<div class="row-fluid">
		<form:form method="post" name="device" action="/admin/device/edit/${device.deviceId}">
			<div class="span4">
				<input type="text" name="deviceId" value="${device.deviceId}" readonly="readonly"></input>
				<input type="text" name="username" value="${device.username}" readonly="readonly"></input>
				<label>Farbe auf Karte:</label>
				<div class="input-append color" data-color="rgb(255, 146, 180)" data-color-format="rgb">
					<input type="text" name="color" value="${device.color}" class="colorpicker"></input>


				</div>

				<label>Anzeigename: </label><input type="text" name="labelName"
					value="${device.labelName}" />

				<button type="submit" class="btn btn-primary">Speichern</button>
			</div>
		</form:form>
	</div>


</body>
</html>