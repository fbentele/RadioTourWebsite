<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="footer black-footer">
	<div class="container">
		<div class="row-fluid muted credit">
			<div class="span4">
				<p><spring:message code="label.footer.copy" /></p>
				<p>
					<spring:message code="label.footer.info" />
				</p>
			</div>
			<div class="span4">
				<ul>
					<li><a href="/agb" target="_self">AGB</a></li>
					<li><a href="/impressum" target="_self">Impressum</a></li>
					<li><a href="/system" target="_self">System</a></li>
				</ul>
			</div>
			<div class="span4">
				<ul>
					<li><a href="/race" target="_self"><spring:message code="label.race" /></a></li>
					<li><a href="/admin" target="_self">Admin</a></li>
					<sec:authorize access="isAuthenticated()">
						<li><a href="/j_spring_security_logout">Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</div>