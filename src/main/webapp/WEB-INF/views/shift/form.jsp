<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Shifts" />
    <jsp:param name="property" value="shift" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="shiftSave" value="/shift/save" />
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

<jsp:include page="/WEB-INF/views/footer.jsp" />