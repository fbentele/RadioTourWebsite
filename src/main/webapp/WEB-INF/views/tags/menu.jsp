<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty menuitems}">
	<div class="span2">
		<div class="">
			<ul class="nav nav-list affix">
				<c:if test="${not empty menutitle}">
					<li class="nav-header">${menutitle}</li>
				</c:if>
				<c:forEach items="${menuitems}" var="item">
					<li class=""><a href="${item.urlpath}" target="_self">${item.menutitle}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
