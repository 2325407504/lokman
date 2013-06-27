<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/role/list" var="role_list" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li><a href="${role_list}"><spring:message code="Roles" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="New Entry" /></li>
    </ul>
<spring:url var="saveUrl" value="/role/save" />
<form:form modelAttribute="roleAttribute" action="${saveUrl}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code" /></form:label>
            <form:input path="code" />
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty roleAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${roleAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty roleAttribute.id }">
    <spring:url var="deleteUrl" value="/role/delete?id=${roleAttribute.id}" />
    <form:form id="form-${roleAttribute.id}" modelAttribute="roleAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>