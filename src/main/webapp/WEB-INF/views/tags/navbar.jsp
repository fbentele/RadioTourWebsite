<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="/">TourLive</a>
				<div class="nav-collapse collapse navbar-inverse-collapse">
					<ul class="nav">
						<c:if test="${not empty races}">
							<li class="dropdown ${navbarrace}"><a href="<c:url value="/race" />"
								class="dropdown-toggle" data-toggle="dropdown"><spring:message
										code="label.actualRaces" /> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<c:forEach items="${races}" var="race">
										<li><a href="/race/${race.raceSlug}">${race.raceName}</a></li>
									</c:forEach>
								</ul></li>
						</c:if>
						<li class="${navbararchive}"><a href="<c:url value="/archive" />"><spring:message
									code="label.archive" /></a></li>
						<li class="${navbarapi}"><a href="<c:url value="/api" />">API</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" data-toggle="dropdown"><spring:message code="label.language" /> <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="?lang=de">Deutsch</a></li>
								<li><a href="?lang=en">English</a></li>
							</ul></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>