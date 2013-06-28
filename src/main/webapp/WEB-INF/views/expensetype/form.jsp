<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="expensetypeList" value="/expensetype/list" />
<spring:url var="expensetypeEdit" value="/expensetype/edit/${expensetypeAttribute.id}" />
<spring:url var="expensetypeNew" value="/expensetype/new" />
<spring:url var="expensetypeSave" value="/expensetype/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expensetypeList}"><spring:message code="Expensetypes" /></a></li>
	<c:choose>
		<c:when test="${ !empty expensetypeAttribute.id }">
			<li class="active"><a href="${expensetypeEdit}"><spring:message code="Entry No" />: ${expensetypeAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${expensetypeNew}"><spring:message code="New Entry" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="expensetypeAttribute" action="${expensetypeSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name" /></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty expensetypeAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${expensetypeAttribute.id}').submit();"><spring:message code="Delete" /></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty expensetypeAttribute.id }">
<spring:url var="deleteUrl" value="/expensetype/delete?id=${expensetypeAttribute.id}" />
<form:form id="form-${expensetypeAttribute.id}" modelAttribute="expensetypeAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />