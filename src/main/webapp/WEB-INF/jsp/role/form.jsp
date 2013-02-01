<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="label.role"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="New entry"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/role/save" />
		<form:form modelAttribute="roleAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="code"><spring:message code="Code"></spring:message></form:label>
					<span class="input"><form:input path="code" /></span>
					<form:errors cssClass="error-field" path="code" />
				</div>
				<div class="form-row">
					<form:label path="name"><spring:message code="label.name"></spring:message></form:label>
					<span class="input"><form:input path="name" /></span>
					<form:errors cssClass="error-field" path="name" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty roleAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${roleAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty roleAttribute.id }">
		<c:url var="deleteUrl" value="/role/delete?id=${roleAttribute.id}" />
		<form:form id="form-${roleAttribute.id}" modelAttribute="roleAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>