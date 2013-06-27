<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="wastegroupList" value="/wastegroup/list" />
<spring:url var="wastegroupEdit" value="/wastegroup/edit/${wastegroupAttribute.id}" />
<spring:url var="wastegroupNew" value="/wastegroup/new" />
<spring:url var="wastegroupSave" value="/wastegroup/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${wastegroupList}"><spring:message code="Waste Groups" /></a></li>
	<c:choose>
		<c:when test="${ !empty wastegroupAttribute.id }">
			<li class="active"><a href="${wastegroupEdit}"><spring:message code="Entry No" />: ${wastegroupAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${wastegroupNew}"><spring:message code="New Entry" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="wastegroupAttribute" action="${wastegroupSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name" /></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty wastegroupAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${wastegroupAttribute.id}').submit();"><spring:message code="Delete" /></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty wastegroupAttribute.id }">
<spring:url var="deleteUrl" value="/wastegroup/delete?id=${wastegroupAttribute.id}" />
<form:form id="form-${wastegroupAttribute.id}" modelAttribute="wastegroupAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>