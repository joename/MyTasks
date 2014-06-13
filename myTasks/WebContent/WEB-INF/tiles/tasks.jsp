<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table class="table table-striped table-condensed">
	<tr>
		<th>id</th>
		<th>Task</th>
		<th>Date</th>
		<th>Last Update</th>
		<th>Responsible</th>
		<th>User</th>
		<th></th>
	</tr>
	<c:forEach var="task" items="${tasks}">
		<tr>
			<td>${task.idTask}</td>
			<td>${task.taskname}</td>
			<td>${task.date}</td>
			<td>${task.timestamp}</td>
			<td>${task.userResponsible.username}</td>
			<td>${task.user.username}</td>
			<td>
				<button type="button" class="btn btn-default btn-xs btn-primary">Edit</button>
				<button type="button" class="btn btn-default btn-xs btn-danger">Delete</button>
			</td>
		</tr>
	</c:forEach>
</table>

<p>
	<a href="<c:url value="/home"/>">Home</a>
</p>