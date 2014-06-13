<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<title><tiles:getAsString name="title" /></title>
		<!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css" rel="stylesheet">
		<tiles:insertAttribute name="includes"></tiles:insertAttribute>
	</head>
<body>
	<div class="content">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="afterfooter" />
</body>
</html>