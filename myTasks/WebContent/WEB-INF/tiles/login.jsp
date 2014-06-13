<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	});
</script>

<p>
	<a href="<c:url value="/createuser"/>">Create new account</a>
</p>
<div class="container">
	<form method="POST"
		action="${pageContext.request.contextPath}/j_spring_security_check"
		class="form-signin" role="form">
		<h2 class="form-signin-heading">Please sign in</h2>
		<input name="j_username" type="text" class="form-control"
			placeholder="Username" required autofocus />
		<input name='j_password' type="password" class="form-control"
			placeholder="Password" required />
		<c:if test="${param.error != null }">
			<div class="alert alert-danger"><strong>Login failed!</strong> Check that your username and
				password are correct.</div>
		</c:if>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
		<label class="checkbox"> <input type="checkbox"
				name="_spring_security_remember_me" value="remember-me">
			Remember me
		</label>
	</form>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->