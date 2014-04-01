<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<p>
	<a href="${pageContext.request.contextPath}/tasks">Show current
		tasks</a>
</p>
<p>
	<a href="${pageContext.request.contextPath}/createtask">Create task</a>
</p>
<p>
	<a href="${pageContext.request.contextPath}/createuser">Create user</a>
</p>
<p>
	<a href="<c:url value="/j_spring_security_logout"/>">Log out</a>
</p>