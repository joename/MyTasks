<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Modal -->
<div class="modal fade" id="newAction" tabindex="-1" role="dialog"
	aria-labelledby="newAction" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Crea nueva acción</h4>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<input type="hidden" name="idTask" value="${idTask}" /> <input
						type="hidden" name="idUser" value="${user.idUser}" />
					<table class="table">
						<tr>
							<td>Fecha</td>
							<td><input type="date" name="fecha" size="16"
								value="${new Date()}" readOnly="true" /></td>
						</tr>
						<tr>
							<td>Duración</td>
							<td><input type="text" size="10" name="duration" /></td>
						</tr>
						<tr>
							<td>Acción</td>
							<td><input type="text" name="actionname" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><input type="text" name="description" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button"
					ng-click="sendnewaction();closeModal('newAction');getactions(${task.idTask})"
					class="btn btn-primary">Guardar acción</button>
			</div>
		</div>
	</div>
</div>