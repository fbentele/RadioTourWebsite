<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>TourLive - Login</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3"></div>
		<div class="span3"></div>
		<div class="span3">
			<div class="login-form">
				<h2><spring:message code="label.login" /></h2>
				<form name="f" action="<c:url value='/j_spring_security_check' />" method="POST">
					<fieldset>
						<div class="clearfix">
							<input type="text" name="j_username" placeholder="<spring:message code="label.username" />" tabindex="1">
						</div>
						<div class="clearfix">
							<input type="password" name="j_password" placeholder="<spring:message code="label.password" />" tabindex="2">
						</div>
						<button class="btn primary" type="submit" tabindex="3"><spring:message code="label.loggingin" /></button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>