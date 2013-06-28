<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="machineList" value="/machine/list" />
<spring:url var="machineEdit" value="/machine/edit/${machineAttribute.id}" />
<spring:url var="machineNew" value="/machine/new" />
<spring:url var="machineSave" value="/machine/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${machineList}"><spring:message code="Machines" /></a></li>
	<c:choose>
		<c:when test="${ !empty machineAttribute.id }">
			<li class="active"><a href="${machineEdit}"><spring:message code="Entry No" />: ${machineAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${machineNew}"><spring:message code="New Entry" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="machineAttribute" action="${machineSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name" /></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty machineAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${machineAttribute.id}').submit();"><spring:message code="Delete" /></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty machineAttribute.id }">
<spring:url var="deleteUrl" value="/machine/delete?id=${machineAttribute.id}" />
<form:form id="form-${machineAttribute.id}" modelAttribute="machineAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />