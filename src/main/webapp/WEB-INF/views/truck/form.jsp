<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trucks" />
    <jsp:param name="property" value="truck" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="truckSave" value="/truck/save" />
<form:form modelAttribute="truckAttribute" action="${truckSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="region"><spring:message code="Region" /></form:label>
            <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
            <form:errors cssClass="text-error" path="region" />
        </div>
        <div class="control-group">
            <form:label path="plate"><spring:message code="Plate" /></form:label>
            <form:input path="plate" />
            <form:errors cssClass="text-error" path="plate" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty truckAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${truckAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty truckAttribute.id }">
    <spring:url var="deleteUrl" value="/truck/delete?id=${truckAttribute.id}" />
    <form:form id="form-${truckAttribute.id}" modelAttribute="truckAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />