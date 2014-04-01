<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Create New User</h2>
<form:form method="POST"
	action="${pageContext.request.contextPath}/docreateuser"
	commandName="user">
	<table class="formtable">
		<tr>
			<td class="label">Username</td>
			<td><form:input class="control" path="username" name="username"
					type="text" /><br />
				<div class="error">
					<form:errors path="username" cssClass="error" />
				</div></td>
		</tr>
		<tr>
			<td class="label">aka</td>
			<td><form:input class="control" path="aka" name="aka" /><br />
				<div class="error">
					<form:errors path="aka" cssClass="error" />
				</div></td>
		</tr>
		<tr>
			<td class="label">Email</td>
			<td><form:input class="control" path="email" name="email" /><br />
				<div class="error">
					<form:errors path="email" cssClass="error" />
				</div></td>
		</tr>
		<tr>
			<td class="label">Password</td>
			<td><form:input class="control" path="password" name="password" type="password" /><br />
				<div class="error">
					<form:errors path="password" cssClass="error" />
				</div></td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="password" />
				<div id="matchpass"></div></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Create new user" type="submit" /></td>
		</tr>
	</table>
</form:form>