<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="random" class="ch.hsr.ba.tourlive.utils.Randomizer" scope="application" />

	<div class="hero-unit" style="background-image:url(resources/img/banner${random.nextNumber % 3}.png)">
		<h2>Title</h2>
	</div>