<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2>LiveTicker: ${stage.stageName}</h2>
	<div class="row-fluid">
		<div class="span6">
			<form:form method="post" name="liveTickerItem"
				action="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/add">
				<label for="timestamp">Zeitpunkt: </label>
				<div id="datetimepicker1" class="input-append date">
					<input data-format="hh:mm:ss" type="datetime" name="timestamp" value="${now}"
						tabindex="1" /> <span class="add-on"> <i data-time-icon="icon-time"
						data-date-icon="icon-calendar"></i></span>
				</div>
				<input type="hidden" name="stage">
				<label for="news">Newseintrag: </label>
				<textarea name="news" tabindex="2"> </textarea>
				<br />
				<button type="submit" class="btn btn-primary">Speichern</button>
			</form:form>
		</div>
		<div class="span4">
			<c:if test="${not empty liveTickerItems}">
				<table class="table table-hover">
					<c:forEach items="${liveTickerItems}" var="lti">
						<tr>
							<td>${lti.timestamp}</td>
							<td>${lti.news}</td>
							<td><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/delete/${lti.liveTickerId}">
									<i class="icon-trash pull-right"></i>
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>