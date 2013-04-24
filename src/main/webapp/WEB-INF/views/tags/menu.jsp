<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty menuitems}">
	<div class="span2">
		<div class="well sidebar-nav">
			<ul class="nav">
				<li class="nav-header">${menutitle}</li>
				<c:forEach items="${menuitems}" var="item">
					<li class=""><a href="${item.urlpath}" target="_self">${item.menutitle}</a></li>
				</c:forEach>
			</ul>
		</div>
		<!--/.well -->
	</div>
	<!--/span-->
</c:if>
