<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Profile"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/profile/save" />
		<form:form modelAttribute="profileAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<fieldset>
				<div class="form-row">
					<form:label path="firstName"><spring:message code="FirstName"></spring:message></form:label>
					<span><form:input path="firstName" /></span>
				</div>       
				<div class="form-row">
					<form:label path="lastName"><spring:message code="LastName"></spring:message></form:label>
					<span><form:input path="lastName" /></span>
				</div>
				<div class="form-row">
					<form:label path="email"><spring:message code="E-mail Address"></spring:message></form:label>
					<span><form:input path="email" /></span>
					<form:errors cssClass="error-field" path="email" />
				</div>
				<div class="form-row">
					<form:label path="username"><spring:message code="Username"></spring:message></form:label>
					<span><form:input path="username" /></span>
					<form:errors cssClass="error-field" path="username" />
				</div>
				<div class="form-row">
					<form:label path="password"><spring:message code="Password"></spring:message></form:label>
					<span><form:password path="password" /></span>
					<form:errors cssClass="error-field" path="password" />
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>