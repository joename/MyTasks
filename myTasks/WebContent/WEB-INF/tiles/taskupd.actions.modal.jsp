<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Modal -->
<div class="modal fade" id="action{{action.idTaskAction}}" tabindex="-1"
	role="dialog" aria-labelledby="action{{action.idTaskAction}}"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Editar acción</h4>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<input type="hidden" ng-model="action.idTaskAction" /> <input
						type="hidden" ng-model="action.idTask" /> <input type="hidden"
						ng-model="action.user.idUser" />
					<table class="table">
						<tr>
							<td>Acción</td>
							<td><input type="text" ng-model="action.actionname" /></td>
						</tr>
						<tr>
							<td>Usuario</td>
							<td><input type="text" ng-model="action.user.username" /></td>
						</tr>
						<tr>
							<td>Fecha</td>
							<td><input type="date" name="fecha"
								placeholder="dd-MM-yyyy HH:mm:ss" size="16"
								ng-model="data.action.date" readOnly="true" /></td>
						</tr>
						<tr>
							<td>Duración</td>
							<td><input type="text" size="10" ng-model="action.duration" /></td>
						</tr>
						<tr>
							<td>Descripción</td>
							<td><input type="text" ng-model="action.description" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<button type="button"
					ng-click="sendaction($index);getactions($index);closeModal(${task.idTask})"
					class="btn btn-primary">Guardar</button>
			</div>
		</div>
	</div>
</div>