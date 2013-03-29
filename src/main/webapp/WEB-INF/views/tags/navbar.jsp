<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="/">TourLive</a>
				<div class="nav-collapse collapse navbar-inverse-collapse">
					<ul class="nav">
						<li class="${navbarhome}"><a href="<c:url value="/" />">Home</a></li>
						<li class="dropdown ${navbartds}"><a href="<c:url value="/tds" />"
							class="dropdown-toggle" data-toggle="dropdown">TDS <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Etappe 1</a></li>
								<li><a href="#">Etappe 2</a></li>
								<li><a href="#">Etappe 3</a></li>
								<li><a href="#">Etappe 4</a></li>
								<li><a href="#">Etappe 5</a></li>
								<li><a href="#">Etappe 6</a></li>
							</ul></li>
						<li class="${navbarrace}"><a href="<c:url value="/race" />">Rennen</a></li>
						<li class="${navbararchive}"><a href="<c:url value="/archive" />">Archive</a></li>
						<li class="${navbarapi}"><a href="<c:url value="/api" />">API</a></li>
						<li class="${navbaradmin}"><a href="<c:url value="/admin" />">Admin</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>