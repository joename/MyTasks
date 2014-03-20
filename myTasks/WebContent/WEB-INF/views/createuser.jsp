<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<title>Create a new user</title>
</head>
<body>
	<form:form method="POST"
		action="${pageContext.request.contextPath}/docreateuser"
		commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">Username</td>
				<td><form:input class="control" path="username" name="username"
						type="text" /><br /> <form:errors path="username"
						cssClass="error" /></td>
			</tr>
			<tr>
				<td class="label">aka</td>
				<td><form:input path="aka" name="aka" /><br /> <form:errors
						path="aka" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="label">Position</td>
				<td><form:input path="position" name="position" /><br /> <form:errors
						path="position" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="label">Email</td>
				<td><form:input path="email" name="email" /><br /> <form:errors
						path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td class="label">Password</td>
				<td><form:input path="password" name="password" /><br /> <form:errors
						path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Create new task" type="submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>