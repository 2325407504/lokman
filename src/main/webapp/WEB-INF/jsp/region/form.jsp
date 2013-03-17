<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="regionList" value="/region/list" />
<spring:url var="regionEdit" value="/region/edit/${regionAttribute.id}" />
<spring:url var="regionNew" value="/region/new" />
<spring:url var="regionSave" value="/region/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${regionList}"><spring:message code="Regions"></spring:message></a></li>
	<c:choose>
		<c:when test="${ !empty regionAttribute.id }">
			<li class="active"><a href="${regionEdit}"><spring:message code="Entry No"></spring:message>: ${regionAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${regionNew}"><spring:message code="New Entry"></spring:message></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="regionAttribute" action="${regionSave}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="name"><spring:message code="Name"></spring:message></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="error-field" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty regionAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${regionAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty regionAttribute.id }">
<spring:url var="deleteUrl" value="/region/delete?id=${regionAttribute.id}" />
<form:form id="form-${regionAttribute.id}" modelAttribute="regionAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>