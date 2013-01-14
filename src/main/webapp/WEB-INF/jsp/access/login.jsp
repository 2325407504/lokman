<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<form class="login-form" action="j_spring_security_check" method="post" >
			<fieldset>
				<legend><spring:message code="label.login"></spring:message></legend>
				
				<p>
				<label for="j_username"><spring:message code="label.username"></spring:message></label>
				<input id="j_username" name="j_username" size="20" maxlength="50" type="text" placeholder="<spring:message code="label.username"></spring:message>"/>
				</p>
				
				<p>
				<label for="j_password"><spring:message code="label.password"></spring:message></label>
				<input id="j_password" name="j_password" size="20" maxlength="50" type="password" placeholder="<spring:message code="label.password"></spring:message>"/>
				</p>
				
				<p>
				<label for="rememberMe">
				<input type="checkbox" name="_spring_security_remember_me" id="rememberMe"/>
				<spring:message code="label.rememberMe"></spring:message>
				</label>
				</p>
				
				<p><button class="btn" type="submit"><spring:message code="button.login"></spring:message></button></p>
			</fieldset>
		</form>
		<p class="message">${message}</p>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>