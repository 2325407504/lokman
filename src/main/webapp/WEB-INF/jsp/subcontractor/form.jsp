<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/subcontractor/list" var="subcontractor_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${subcontractor_list}"><spring:message code="Subcontractors"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="New Entry"></spring:message></li>
</ul>
<spring:url var="saveUrl" value="/subcontractor/save" />
<form:form modelAttribute="subcontractorAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="name"><spring:message code="Name"></spring:message></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="error-field" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty subcontractorAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${subcontractorAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty subcontractorAttribute.id }">
<spring:url var="deleteUrl" value="/subcontractor/delete?id=${subcontractorAttribute.id}" />
<form:form id="form-${subcontractorAttribute.id}" modelAttribute="subcontractorAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>