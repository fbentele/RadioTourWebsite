<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<div class="row-fluid">
		<div class="span12">
			<hr class="soften">
			<div class="row-fluid">
				<div class="span4">
				<ul>
				<c:forEach items="${stages}" var="stage">
					<li><a href="/race/${stage.race.raceSlug}/stage/${stage.stageSlug}">${stage.stageName}</a></li>
				</c:forEach>
				</ul>
				</div>
				<div class="span4">
				</div>
			</div>
		</div>
	</div>
</body>
</html>