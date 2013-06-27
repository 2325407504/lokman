<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="wasteList" value="/waste/list" />
<spring:url var="wasteEdit" value="/waste/edit/${wasteAttribute.id}" />
<spring:url var="wasteNew" value="/waste/new" />
<spring:url var="wasteSave" value="/waste/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${wasteList}"><spring:message code="Wastes" /></a></li>
        <c:choose>
            <c:when test="${ !empty wasteAttribute.id }">
            <li class="active"><a href="${wasteEdit}"><spring:message code="Entry No" />: ${wasteAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${wasteNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<form:form modelAttribute="wasteAttribute" action="${wasteSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="wastegroup"><spring:message code="Waste Group" /></form:label>
            <form:select path="wastegroup.id" multiple="false" items="${wastegroups}" itemLabel="name" itemValue="id"/>
            <form:errors cssClass="text-error" path="wastegroup" />
        </div>
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
            <c:if test="${ !empty wasteAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${wasteAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty wasteAttribute.id }">
    <spring:url var="deleteUrl" value="/waste/delete?id=${wasteAttribute.id}" />
    <form:form id="form-${wasteAttribute.id}" modelAttribute="wasteAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>