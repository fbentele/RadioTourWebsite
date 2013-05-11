<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${not empty menuitems}">
	<div id="menu-navi" class="span2 bs-docs-sidebar">
		<ul class="well nav">
			<c:if test="${not empty menutitle}">
				<li class="nav-header">${menutitle}</li>
			</c:if>
			<c:forEach items="${menuitems}" var="item">
				<li id="menu-${item.menutitle}" class=""><a href="${item.urlpath}" target="_self">${item.menutitle}</a></li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty adminmenu}">
	<div id="menu-navi" class="span2 bs-docs-sidebar">
		<ul class="well nav">
				<li id="menu-races" class=""><a href="/admin/race" target="_self"><spring:message code="label.race" /></a></li>
				<li id="menu-devices" class=""><a href="/admin/device" target="_self"><spring:message code="label.admin.devices" /></a></li>
		</ul>
	</div>
</c:if>

