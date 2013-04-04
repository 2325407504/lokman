<%@ include file="/WEB-INF/jsp/header.jsp" %>

<form class="form-signin" action="j_spring_security_check" method="post">
	<h2 class="form-signin-heading"><spring:message code="Login"></spring:message></h2>
	<input type="text" class="input-block-level" name="j_username" placeholder="<spring:message code="Username"></spring:message>">
	<input type="password" class="input-block-level" name="j_password" placeholder="<spring:message code="Password"></spring:message>">
	<label class="checkbox">
		<input type="checkbox" name="_spring_security_remember_me">
		<spring:message code="Remember me"></spring:message>
	</label>
	<button class="btn btn-large btn-primary" type="submit"><spring:message code="Login"></spring:message></button>
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>