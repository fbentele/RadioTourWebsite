<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty menuitems}">
	<div id="menu-navi" class="span2 bs-docs-sidebar">
		<ul class="well nav nav-pills nav-stacked affix">
			<c:if test="${not empty menutitle}">
				<li class="nav-header">${menutitle}</li>
			</c:if>
			<c:forEach items="${menuitems}" var="item">
				<li id="menu-${item.menutitle}" class=""><a href="${item.urlpath}" target="_self">${item.menutitle}</a></li>
			</c:forEach>
		</ul>
	</div>
</c:if>

