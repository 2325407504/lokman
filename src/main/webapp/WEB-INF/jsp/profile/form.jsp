<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Profile"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/user/save" />
		<form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<fieldset>
				<div class="form-row">
					<label for="firstname"><spring:message code="Firstname"></spring:message></label>
					<span><form:input path="firstname" /></span>
				</div>       
				<div class="form-row">
					<label for="lastname"><spring:message code="Lastname"></spring:message></label>
					<span><form:input path="lastname" /></span>
				</div>
				<div class="form-row">
					<label for="username"><spring:message code="label.username"></spring:message></label>
					<span><form:input path="username" /></span>
					<form:errors cssClass="error-field" path="username" />
				</div>
				<div class="form-row">
					<label for="password"><spring:message code="label.password"></spring:message></label>
					<span><form:password path="password" /></span>
					<form:errors cssClass="error-field" path="password" />
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>