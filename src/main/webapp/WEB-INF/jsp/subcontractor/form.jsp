<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="subcontractorList" value="/subcontractor/list" />
<spring:url var="subcontractorEdit" value="/subcontractor/edit/${subcontractorAttribute.id}" />
<spring:url var="subcontractorNew" value="/subcontractor/new" />
<spring:url var="subcontractorSave" value="/subcontractor/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${subcontractorList}"><spring:message code="Subcontractors"></spring:message></a></li>
	<c:choose>
		<c:when test="${ !empty subcontractorAttribute.id }">
			<li class="active"><a href="${subcontractorEdit}"><spring:message code="Entry No"></spring:message>: ${subcontractorAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${subcontractorNew}"><spring:message code="New Entry"></spring:message></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="subcontractorAttribute" action="${subcontractorSave}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="region"><spring:message code="Region"></spring:message></form:label>
			<form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
			<form:errors cssClass="error-field" path="region" />
		</div>
		<div class="form-row">
			<form:label path="name"><spring:message code="Name"></spring:message></form:label>
			<span class="input"><form:input path="name" /></span>
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