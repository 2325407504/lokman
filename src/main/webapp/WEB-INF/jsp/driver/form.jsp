<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="Driver"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="New entry"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/driver/save" />
		<form:form modelAttribute="driverAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="firstName"><spring:message code="FirstName"></spring:message></form:label>
					<span><form:input path="firstName" /></span>
					<form:errors cssClass="error-field" path="firstName" />
				</div>       
				<div class="form-row">
					<form:label path="lastName"><spring:message code="LastName"></spring:message></form:label>
					<span><form:input path="lastName" /></span>
					<form:errors cssClass="error-field" path="lastName" />
				</div>
				<div class="form-row">
					<form:label path="phonenumber"><spring:message code="Phone Number"></spring:message></form:label>
					<span><form:input path="phonenumber" /></span>
					<form:errors cssClass="error-field" path="phonenumber" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty driverAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${driverAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
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