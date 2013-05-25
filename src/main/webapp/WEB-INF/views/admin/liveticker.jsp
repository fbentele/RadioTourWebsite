<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<h2>LiveTicker: ${stage.stageName}</h2>
	<div class="row-fluid">
		<div class="span6">
			<c:choose>
				<c:when test="${not empty lti}">
					<form:form method="post" name="liveTickerItem"
						action="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/add">
						<input type="hidden" value="${lti.liveTickerId}" name="liveTickerId" />
						<label for="timestamp"><spring:message code="label.admin.timestamp" />: </label>
						<div id="datetimepicker1" class="input-append date">
							<input data-format="hh:mm:ss" type="datetime" name="timestamp"
								value="${lti.timestamp}" tabindex="1" /> <span class="add-on"> <i
								data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
						</div>
						<input type="hidden" name="stage">
						<label for="news"><spring:message code="label.admin.newsEntry" />: </label>
						<textarea name="news" tabindex="2">${lti.news}</textarea>
						<br />
						<button type="submit" class="btn btn-primary">
							<spring:message code="label.admin.save" />
						</button>
					</form:form>
				</c:when>
				<c:otherwise>
					<form:form method="post" name="liveTickerItem"
						action="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/add">
						<label for="timestamp"><spring:message code="label.admin.timestamp" />: </label>
						<div id="datetimepicker1" class="input-append date">
							<input data-format="hh:mm:ss" type="datetime" name="timestamp" value="${now} dafux"
								tabindex="1" /> <span class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"></i></span>
						</div>
						<input type="hidden" name="stage">
						<label for="news"><spring:message code="label.admin.newsEntry" />: </label>
						<textarea name="news" tabindex="2"></textarea>
						<br />
						<button type="submit" class="btn btn-primary">
							<spring:message code="label.admin.save" />
						</button>
					</form:form>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="span4">
			<c:if test="${not empty liveTickerItems}">
				<table class="table table-hover" data-provides="rowlink">
					<c:forEach items="${liveTickerItems}" var="lti">
						<tr>
							<td><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/${lti.liveTickerId}">${lti.timestamp}</a></td>
							<td>${lti.news}</td>
							<td class="nolink"><a
								href="/admin/race/${race.raceId}/stage/${stage.stageId}/liveticker/delete/${lti.liveTickerId}">
									<i class="icon-trash pull-right"></i>
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>

	<!-- DateTime Picker -->
	<script type="text/javascript">
		// datetimepicker
		// http://tarruda.github.io/bootstrap-datetimepicker/
		$.fn.datetimepicker.defaults = {
			maskInput : true, // disables the text input mask
			pickDate : false, // disables the date picker
			pickTime : true, // disables de time picker
			pick12HourFormat : false, // enables the 12-hour format time picker
			pickSeconds : true, // disables seconds in the time picker
			startDate : -Infinity, // set a minimum date
			endDate : Infinity
		};
		$(function() {
			$('#datetimepicker1').datetimepicker({
				language : 'de-CH'
			});
			$('#datetimepicker2').datetimepicker({
				language : 'de-CH'
			});
		});
	</script>
</body>
</html>