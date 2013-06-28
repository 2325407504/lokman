<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="quotaList" value="/quota/list" />
<spring:url var="quotaEdit" value="/quota/edit/${quotaAttribute.id}" />
<spring:url var="quotaNew" value="/quota/new" />
<spring:url var="quotaSave" value="/quota/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${quotaList}"><spring:message code="Quotas" /></a></li>
	<c:choose>
		<c:when test="${ !empty quotaAttribute.id }">
			<li class="active"><a href="${quotaEdit}"><spring:message code="Entry No" />: ${quotaAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${quotaNew}"><spring:message code="New Entry" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="quotaAttribute" action="${quotaSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="code"><spring:message code="Code" /></form:label>
			<span><form:input path="code" /></span>
			<form:errors cssClass="text-error" path="code" />
		</div>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name" /></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty quotaAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${quotaAttribute.id}').submit();"><spring:message code="Delete" /></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty quotaAttribute.id }">
<spring:url var="deleteUrl" value="/quota/delete?id=${quotaAttribute.id}" />
<form:form id="form-${quotaAttribute.id}" modelAttribute="quotaAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />