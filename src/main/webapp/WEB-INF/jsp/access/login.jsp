<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<form class="login-form" action="j_spring_security_check" method="post" >
			<fieldset>
				<legend><spring:message code="label.login" text="label.login"></spring:message></legend>
				
				<p>
				<label for="j_username"><spring:message code="Username" text="Username"></spring:message></label>
				<input id="j_username" name="j_username" size="20" maxlength="50" type="text" placeholder="<spring:message code="Username" text="Username"></spring:message>"/>
				</p>
				
				<p>
				<label for="j_password"><spring:message code="Password" text="Password"></spring:message></label>
				<input id="j_password" name="j_password" size="20" maxlength="50" type="password" placeholder="<spring:message code="Password" text="Password"></spring:message>"/>
				</p>
				
				<p>
				<label for="rememberMe">
				<input type="checkbox" name="_spring_security_remember_me" id="rememberMe"/>
				<spring:message code="Remember me" text="Remember me"></spring:message>
				</label>
				</p>
				
				<p><button class="btn" type="submit"><spring:message code="button.login" text="button.login"></spring:message></button></p>
			</fieldset>
		</form>
		<p class="message">${message}</p>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>