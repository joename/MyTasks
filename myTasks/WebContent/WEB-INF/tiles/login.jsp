<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Init Container -->
<div class="container">
	<h2 class="form-signin-heading">Inicia sessión</h2>
	<form method="POST" action="<c:url value="/j_spring_security_check" />"
		class="form-signin" role="form">
		<input name="j_username" type="text" class="form-control"
			placeholder="Username" required autofocus /> <input
			name='j_password' type="password" class="form-control"
			placeholder="Password" required />
		<c:if test="${param.error != null }">
			<div class="alert alert-danger">
				<strong>Login failed!</strong> Check that your username and password
				are correct.
			</div>
		</c:if>
		<label class="checkbox"> <input type="checkbox"
			checked="checked" name="_spring_security_remember_me"
			value="remember-me"> Remember me
		</label>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
	</form>
</div>
<!-- end container -->