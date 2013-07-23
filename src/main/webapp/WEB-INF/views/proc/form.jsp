<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Procedures" />
    <jsp:param name="property" value="proc" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/proc/save" />
<form:form modelAttribute="procAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="active"><spring:message code="Status" /></form:label>
            <form:checkbox path="active" />
            <form:errors cssClass="text-error" path="active" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" cssClass="input-block-level" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="control-group">
            <form:label path="description"><spring:message code="Description" /></form:label>
            <form:textarea path="description" cssClass="input-block-level" />
            <form:errors cssClass="text-error" path="description" />
        </div>
        <div class="control-group">
            <form:label path="content"><spring:message code="Content" /></form:label>
            <form:textarea path="content" />
            <form:errors cssClass="text-error" path="content" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty procAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${procAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty procAttribute.id }">
    <spring:url var="delete" value="/proc/delete">
        <spring:param name="id" value="${procAttribute.id}" />
    </spring:url>
    <form:form id="form-${procAttribute.id}" modelAttribute="procAttribute" action="${delete}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<script type="text/javascript">
    $("[name=content]").redactor();
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />