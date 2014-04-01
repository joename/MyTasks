<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST"
	action="${pageContext.request.contextPath}/docreatetask"
	commandName="task">
	<table class="formtable">
		<tr>
			<td class="label">User</td>
			<td><form:input class="control" path="taskname" name="taskname"
					type="text" /><br /> <form:errors path="taskname"
					cssClass="error" /></td>
		</tr>
		<tr>
			<td class="label">Description</td>
			<td><form:textarea path="description" name="description"></form:textarea><br />
				<form:errors path="description" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="label">Date (dd/mm/yyyy)</td>
			<td><form:input path="date" name="date" type="date" /><br /> <form:errors
					path="date" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="label">Deadline (dd/mm/yyyy)</td>
			<td><form:input path="deadline" name="deadline" type="date" /><br />
				<form:errors path="deadline" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Category</td>
			<td><form:select path="idCategory" name="idCategory">
					<form:option value="0" label="Select" />
					<form:options items="${categories}" itemValue="id"
						itemLabel="category" />
				</form:select><br /> <form:errors path="idCategory" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Priority</td>
			<td><form:select path="idPriority" name="idPriority">
					<form:option value="0" label="Select" />
					<form:options items="${priorities}" itemValue="id"
						itemLabel="priority" />
				</form:select><br /> <form:errors path="idPriority" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">State</td>
			<td><form:select path="idState" name="idState">
					<form:option value="0" label="Select" />
					<form:options items="${states}" itemValue="id" itemLabel="state" />
				</form:select><br /> <form:errors path="idState" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Pending by</td>
			<td><form:select path="pending" name="pending">
					<form:option value="1" selected="selected">Responsible</form:option>
					<form:option value="0">Applicant</form:option>
				</form:select><br /> <form:errors path="pending" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">User</td>
			<td><form:select path="idUser">
					<form:option value="0" label="Select" />
					<form:options items="${users}" itemValue="id" itemLabel="user" />
				</form:select><br /> <form:errors path="idUser" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">User responsible</td>
			<td><form:select path="idUserResponsible">
					<form:option value="0" label="Select" />
					<form:options items="${users}" itemValue="id" itemLabel="user" />
				</form:select><br /> <form:errors path="idUserResponsible" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Evaluation</td>
			<td><form:textarea path="evaluation" name="evaluation"></form:textarea><br />
				<form:errors path="evaluation" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Create new task" type="submit" /></td>
		</tr>
	</table>
</form:form>