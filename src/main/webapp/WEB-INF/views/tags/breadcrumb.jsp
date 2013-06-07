<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<body>
	<c:if test="${not empty breadcrumb}">
		<ul class="breadcrumb">
			<li><span class="divider"> </span></li>
			<c:forEach items="${breadcrumb.parentPages}" var="page">
				<li><a href="${page.link}">${page.title}</a> <span class="divider">/</span></li>
			</c:forEach>
			<li class="active">${breadcrumb.activepage.title}</li>
		</ul>
	</c:if>
</body>
</html>