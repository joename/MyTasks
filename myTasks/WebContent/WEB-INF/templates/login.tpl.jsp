<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<title><tiles:getAsString name="title" /></title>
		<link
			href="${pageContext.request.contextPath}/static/dist/css/bootstrap.min.css"
			rel="stylesheet">
		<tiles:insertAttribute name="includes"></tiles:insertAttribute>
	</head>
<body>
	<tiles:insertAttribute name="body" />
	<!-- JavaScript =============================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<tiles:insertAttribute name="bottom" />
</body>
</html>