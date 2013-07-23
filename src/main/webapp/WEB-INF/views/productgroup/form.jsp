<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productgroups" />
    <jsp:param name="property" value="productgroup" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="productgroupSave" value="/productgroup/save" />
<form:form modelAttribute="productgroupAttribute" action="${productgroupSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" />
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