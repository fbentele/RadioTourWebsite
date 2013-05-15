<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty stage.bannerImage}">
		<div class="hero-unit" style="background-image:url(${hostname}${stage.bannerImage})">
		<h2>
		<span style="opacity: 0.1">Tourlive</span>
	</h2>
</div>
	</c:when>
	<c:otherwise>
		<div class="hero-unit" style="background-image: url(/resources/img/banner0.png)">
		<h2>
		<span style="opacity:0.1">Tourlive</span>
	</h2>
</div>
	</c:otherwise>
</c:choose>