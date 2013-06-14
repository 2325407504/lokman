<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="electricmeterList" value="/electricmeter/list" />
<spring:url var="electricmeterEdit" value="/electricmeter/edit/${electricmeterAttribute.id}" />
<spring:url var="electricmeterNew" value="/electricmeter/new" />
<spring:url var="electricmeterSave" value="/electricmeter/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${electricmeterList}"><spring:message code="Electricmeters"></spring:message></a></li>
	<c:choose>
		<c:when test="${ !empty electricmeterAttribute.id }">
			<li class="active"><a href="${electricmeterEdit}"><spring:message code="Entry No"></spring:message>: ${electricmeterAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${electricmeterNew}"><spring:message code="New Entry"></spring:message></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="electricmeterAttribute" action="${electricmeterSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name"></spring:message></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty electricmeterAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${electricmeterAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty electricmeterAttribute.id }">
<spring:url var="deleteUrl" value="/electricmeter/delete?id=${electricmeterAttribute.id}" />
<form:form id="form-${electricmeterAttribute.id}" modelAttribute="electricmeterAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>