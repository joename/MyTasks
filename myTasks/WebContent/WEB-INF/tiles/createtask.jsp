<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST"
	action="${pageContext.request.contextPath}/docreatetask"
	commandName="task">
	<table class="formtable">
		<tr>
			<td class="label">Task</td>
			<td><form:input cssClass="control" path="taskname"
					name="taskname" type="text" /><br /> <form:errors path="taskname"
					cssClass="error" /></td>
		</tr>
		<tr>
			<td class="label">Description</td>
			<td><form:textarea cssClass="control" path="description"
					name="description"></form:textarea><br /> <form:errors
					path="description" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="label">Deadline (dd/mm/yyyy)</td>
			<td><form:input cssClass="control" path="deadline"
					name="deadline" type="date" /><br /> <form:errors path="deadline"
					cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Category</td>
			<td><form:select cssClass="control" path="idCategory"
					name="idCategory">
					<form:option value="0" label="Select" />
					<form:options items="${categories}" itemValue="idTaskCategory"
						itemLabel="categoryname" />

				</form:select><br /> <form:errors path="idCategory" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Priority</td>
			<td><form:select cssClass="control" path="idPriority"
					name="idPriority">
					<form:option value="1" label="Low" />
					<form:options  items="${priorities}" itemValue="idTaskPriority"
						itemLabel="priorityname" />
				</form:select><br />
			<form:errors path="idPriority" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">State</td>
			<td><form:select cssClass="control" path="idState"
					name="idState">
					<form:option value="1" label="Pending" />
					<form:options items="${states}" itemValue="idTaskState"
						itemLabel="statename" />
				</form:select><br /> <form:errors path="idState" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Pending by</td>
			<td><form:select cssClass="control" path="pending"
					name="pending">
					<form:option value="1" selected="selected">Responsible</form:option>
					<form:option value="0">Applicant</form:option>
				</form:select><br /> <form:errors path="pending" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">User responsible</td>
			<td><form:select cssClass="control" path="idUserResponsible">
					<form:option value="0" label="Select" />
					<form:options items="${users}" itemValue="idUser"
						itemLabel="username" />
				</form:select><br /> <form:errors path="idUserResponsible" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label">Evaluation</td>
			<td><form:textarea cssClass="control" path="evaluation"
					name="evaluation"></form:textarea><br /> <form:errors
					path="evaluation" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Create new task" type="submit" /></td>
		</tr>
	</table>
</form:form>