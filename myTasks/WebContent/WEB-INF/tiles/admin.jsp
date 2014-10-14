<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

Admin page
Authorised users only!
<table class="formtable">
	<tr>
		<td class="control">id</td>
		<td class="control">Username</td>
		<td class="control">Aka</td>
		<td class="control">Enabled</td>
		<td class="control">Timestamp</td>
	</tr>
	<c:forEach var="user" items="${users}">
		<tr>
			<td class="control">${user.idPerson}</td>
			<td class="control">${user.username}</td>
			<td class="control">${user.aka}</td>
			<td class="control">${user.enabled}</td>
			<td class="control">${user.timestamp}</td>
		</tr>
	</c:forEach>
</table>