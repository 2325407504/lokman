<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="shiftList" value="/shift/list" />
<spring:url var="shiftEdit" value="/shift/edit/${shiftAttribute.id}" />
<spring:url var="shiftNew" value="/shift/new" />
<spring:url var="shiftSave" value="/shift/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${shiftList}"><spring:message code="Shifts" /></a></li>
        <c:choose>
            <c:when test="${ !empty shiftAttribute.id }">
            <li class="active"><a href="${shiftEdit}"><spring:message code="Entry No" />: ${shiftAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${shiftNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<form:form modelAttribute="shiftAttribute" action="${shiftSave}" method="post">
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
            <c:if test="${ !empty shiftAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${shiftAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty shiftAttribute.id }">
    <spring:url var="deleteUrl" value="/shift/delete?id=${shiftAttribute.id}" />
    <form:form id="form-${shiftAttribute.id}" modelAttribute="shiftAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>