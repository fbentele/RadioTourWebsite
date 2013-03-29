<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Tourlive</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="TourLive System">
<meta name="author" content="TourLive Team and Students">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/bootstrap-responsive.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/tourlive.css" />"
	rel="stylesheet" type="text/css" />

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<c:import url="/WEB-INF/views/tags/navbar.jsp" />
	<div class="container">
		<c:import url="/WEB-INF/views/tags/banner.jsp" />
		<div class="row-fluid">
			<c:import url="/WEB-INF/views/tags/menu.jsp" />
			<decorator:body />
			<!--/span-->
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	<c:import url="/WEB-INF/views/tags/footer.jsp" />
	<c:import url="/WEB-INF/views/tags/javascripts.jsp" />
</body>
</html>