<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="Driver"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="button.new"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/driver/save" />
		<form:form modelAttribute="driverAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<label for="firstname"><spring:message code="Firstname"></spring:message></label>
					<span><form:input path="firstname" /></span>
					<form:errors cssClass="error-field" path="firstname" />
				</div>       
				<div class="form-row">
					<label for="lastname"><spring:message code="Lastname"></spring:message></label>
					<span><form:input path="lastname" /></span>
					<form:errors cssClass="error-field" path="lastname" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty driverAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${driverAttribute.id}').submit();"><spring:message code="button.delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty driverAttribute.id }">
		<c:url var="deleteUrl" value="/driver/delete?id=${driverAttribute.id}" />
		<form:form id="form-${driverAttribute.id}" modelAttribute="driverAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>