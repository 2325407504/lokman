<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Regions" />
    <jsp:param name="property" value="region" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="regionSave" value="/region/save" />
<form:form modelAttribute="regionAttribute" action="${regionSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty regionAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${regionAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty regionAttribute.id }">
    <spring:url var="deleteUrl" value="/region/delete?id=${regionAttribute.id}" />
    <form:form id="form-${regionAttribute.id}" modelAttribute="regionAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />