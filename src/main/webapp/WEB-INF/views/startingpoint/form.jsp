<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Starting Points" />
    <jsp:param name="property" value="startingpoint" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="startingpointSave" value="/startingpoint/save" />
<form:form modelAttribute="startingpointAttribute" action="${startingpointSave}" method="post">
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
            <c:if test="${ !empty startingpointAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${startingpointAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty startingpointAttribute.id }">
    <spring:url var="deleteUrl" value="/startingpoint/delete?id=${startingpointAttribute.id}" />
    <form:form id="form-${startingpointAttribute.id}" modelAttribute="startingpointAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />