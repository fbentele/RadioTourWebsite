<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>TourLive - Login</title>
</head>
<body>
	<div class="row-fluid">
		<div class="login-form">
			<h2>
				<spring:message code="label.login" />
			</h2>
			<form name='f' action="/j_spring_security_check" method='POST'>
				<table>
					<tr>
						<td>User:</td>
						<td><input type='text' name='j_username' value=''></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='j_password' /></td>
					</tr>
					<tr>
						<td><input name="submit" type="submit" value="submit" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>