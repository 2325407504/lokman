<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="row-fluid">
	<div class="span4 offset4">
		<form class="well login-form" action="j_spring_security_check" method="post" >
			<fieldset>
				<legend><spring:message code="Login" text="Login"></spring:message></legend>
				<hr>
				<label for="j_username"><spring:message code="Username" text="Username"></spring:message></label>
				<input id="j_username" name="j_username" size="20" maxlength="50" type="text" placeholder="<spring:message code="Username" text="Username"></spring:message>"/>
				<label for="j_password"><spring:message code="Password" text="Password"></spring:message></label>
				<input id="j_password" name="j_password" size="20" maxlength="50" type="password" placeholder="<spring:message code="Password" text="Password"></spring:message>"/>
				<label for="rememberMe">
					<input type="checkbox" name="_spring_security_remember_me" id="rememberMe"/>
					<spring:message code="Remember me" text="Remember me"></spring:message>
				</label>
				<button class="btn" type="submit"><spring:message code="Login" text="Login"></spring:message></button>
			</fieldset>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>