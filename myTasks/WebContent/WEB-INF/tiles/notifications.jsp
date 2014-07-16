<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="notificationspre"
	value="${fn:substring(notifications, 0,1)}" />
<c:set var="notificationspost"
	value="${fn:substring(notifications, 1, fn:length(myValue)-1)}" />
<div id="notifications">
	<c:if test="${!empty notifications}">
		<c:if test="${notificationspre == '+' }">
			<div class="alert alert-success" role="alert">
				<c:out value="${notificationspost}" />
			</div>
		</c:if>
		<c:if test="${notificationspre == '='}">
			<div class="alert alert-warning" role="alert">
				<c:out value="${notificationspost}" />
			</div>
		</c:if>
		<c:if test="${notificationspre == '-' }">
			<div class="alert alert-danger" role="alert">
				<c:out value="${notificationspost}" />
			</div>
		</c:if>
		<c:if test="${notificationspre == '!' }">
			<div class="alert alert-info" role="alert">
				<c:out value="${notificationspost}" />
			</div>
		</c:if>
	</c:if>
</div>