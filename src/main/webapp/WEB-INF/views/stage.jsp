<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>All Stages</title>
</head>
<body>

<h2>Etappen</h2>
<a href="stage/new">new</a>
<table>
	<tr>
	<td>
	What?
	</td>
	<td>Where?</td>
	</tr>
<c:forEach items="${stages}" var="stage">
    <tr>

        <td>${stage.stageId}. ${stage.stageName}  </td>
     
        <td><a href="stage/delete/${stage.stageId}">delete</a></td>
    </tr>
</c:forEach>
 </table>
</body>
</html>