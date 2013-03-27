<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="#">TourLive</a>
<div class="nav-collapse collapse">
	<ul class="nav">
		<li class="active"><a href="<c:url value="/" />">Home</a></li>
		<li><a href="<c:url value="/tds" />">TDS</a></li>
		<li><a href="<c:url value="/race" />">Rennen</a></li>
		<li><a href="<c:url value="/archive" />">Archive</a></li>
		<li><a href="<c:url value="/api" />">API</a></li>
		<li><a href="<c:url value="/about" />">About</a></li>
		<li><a href="<c:url value="/admin" />">Admin</a></li>

	</ul>
</div>
