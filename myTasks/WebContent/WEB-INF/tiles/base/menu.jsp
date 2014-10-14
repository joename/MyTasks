<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="menu" class="col-md-2">
	<p>
		<a href="<c:url value='/task/add'/>">Nueva tarea</a>
	</p>
	<p>
		<a href="<c:url value='/task/lst'/>">Listado de tareas</a>
	</p>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p>
			<a href="<c:url value='/user/add'/>">Nuevo usuario</a>
		</p>
		<p>
			<a href="<c:url value='/admin' />">Administrador</a>
		</p>
	</sec:authorize>
</div>
