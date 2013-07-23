<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Electricmeters" />
    <jsp:param name="property" value="electricmeter" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="electricmeterSave" value="/electricmeter/save" />
<form:form modelAttribute="electricmeterAttribute" action="${electricmeterSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty electricmeterAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${electricmeterAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty electricmeterAttribute.id }">
    <spring:url var="deleteUrl" value="/electricmeter/delete?id=${electricmeterAttribute.id}" />
    <form:form id="form-${electricmeterAttribute.id}" modelAttribute="electricmeterAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />