<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><decorator:title default="TourLive" /></title>
<decorator:head />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="TourLive System">
<meta name="author" content="TourLive Team and Students">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/resources/css/tourlive.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-rowlink.min.css" />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet"
	type="text/css" />
<c:import url="/WEB-INF/views/tags/javascripts.jsp" />

<!-- for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body id="tourlive-site">
	<div id="wrap">
		<div class="container">
			<c:import url="/WEB-INF/views/tags/navbar.jsp" />
			<c:import url="/WEB-INF/views/tags/banner.jsp" />
			<div class="row">
				<c:import url="/WEB-INF/views/tags/menu.jsp" />
				<div class="span10 offset2">
					<c:import url="/WEB-INF/views/tags/breadcrumb.jsp" />
					<decorator:body />
				</div>
			</div>
		</div>
		<div id="push"></div>
	</div>
	<c:import url="/WEB-INF/views/tags/footer.jsp" />
</body>
</html>