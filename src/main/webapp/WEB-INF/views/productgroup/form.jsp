<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="productgroupList" value="/productgroup/list" />
<spring:url var="productgroupEdit" value="/productgroup/edit/${productgroupAttribute.id}" />
<spring:url var="productgroupNew" value="/productgroup/new" />
<spring:url var="productgroupSave" value="/productgroup/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${productgroupList}"><spring:message code="Product Groups" /></a></li>
	<c:choose>
		<c:when test="${ !empty productgroupAttribute.id }">
			<li class="active"><a href="${productgroupEdit}"><spring:message code="Entry No" />: ${productgroupAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${productgroupNew}"><spring:message code="New Entry" /></a></li>
		</c:otherwise>
	</c:choose>
</ul>

<form:form modelAttribute="productgroupAttribute" action="${productgroupSave}" method="post">
	<form:errors path="*" cssClass="alert alert-error" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="control-group">
			<form:label path="name"><spring:message code="Name" /></form:label>
			<span><form:input path="name" /></span>
			<form:errors cssClass="text-error" path="name" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty productgroupAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${productgroupAttribute.id}').submit();"><spring:message code="Delete" /></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty productgroupAttribute.id }">
<spring:url var="deleteUrl" value="/productgroup/delete?id=${productgroupAttribute.id}" />
<form:form id="form-${productgroupAttribute.id}" modelAttribute="productgroupAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />