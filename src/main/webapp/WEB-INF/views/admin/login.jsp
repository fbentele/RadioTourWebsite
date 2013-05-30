<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>TourLive - Login</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span4 well">
			<h2>
				<spring:message code="label.login" />
			</h2>
			<form name='f' action="/j_spring_security_check" method='POST'>
				<fieldset>
					<div class="clearfix">
						<input type='text' name='j_username' value=''  placeholder="<spring:message code="label.username" />">
					</div>
					<div class="clearfix">
						
						<input type='password' name='j_password' placeholder="<spring:message code="label.password" />"/>
						<input name="submit" type="submit"
							class="btn" value="Login" />
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>