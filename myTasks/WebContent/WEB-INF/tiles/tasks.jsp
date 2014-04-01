<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table class="formtable">
	<tr>
		<td class="control">id</td>
		<td class="control">Task</td>
		<td class="control">Last Update</td>
		<td class="control">Responsible</td>
		<td class="control">User</td>
	</tr>
	<c:forEach var="task" items="${tasks}">
		<tr>
			<td class="control">${task.id}</td>
			<td class="control">${task.taskname}</td>
			<td class="control">${task.timestamp}</td>
			<td class="control">${task.idUserResponsible}</td>
			<td class="control">${task.idUser}</td>
		</tr>
	</c:forEach>
</table>
