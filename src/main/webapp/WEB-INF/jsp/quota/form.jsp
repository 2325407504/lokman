<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/quota/list" var="quota_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${quota_list}"><spring:message code="Quotas"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="New Entry"></spring:message></li>
</ul>
<spring:url var="saveUrl" value="/quota/save" />
<form:form modelAttribute="quotaAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="name"><spring:message code="Name"></spring:message></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="error-field" path="name" />
		</div>       
		<div class="form-actions">
			<c:if test="${ !empty quotaAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${quotaAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty quotaAttribute.id }">
<spring:url var="deleteUrl" value="/quota/delete?id=${quotaAttribute.id}" />
<form:form id="form-${quotaAttribute.id}" modelAttribute="quotaAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>