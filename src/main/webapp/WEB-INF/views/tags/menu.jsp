<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty menuitems}">
	<div class="span2">
		<div class="well sidebar-nav">
			<ul class="nav nav-list">
				<li class="nav-header">Add new</li>
				<c:forEach items="${menuitems}" var="item">
					<li><a href="${item}" target="_self">${item}</a></li>
				</c:forEach>
			</ul>
		</div>
		<!--/.well -->
	</div>
	<!--/span-->
</c:if>
