<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="label.role"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="button.new"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/role/save" />
		<form:form modelAttribute="roleAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<label for="code"><spring:message code="label.code"></spring:message></label>
					<span class="input"><form:input path="code" /></span>
					<form:errors cssClass="error-field" path="code" />
				</div>
				<div class="form-row">
					<label for="name"><spring:message code="label.name"></spring:message></label>
					<span class="input"><form:input path="name" /></span>
					<form:errors cssClass="error-field" path="name" />
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>