<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Drivers" />
    <jsp:param name="property" value="driver" />
    <jsp:param name="active" value="form" />
</jsp:include>

<form:form modelAttribute="driverAttribute" action="${driverSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="region"><spring:message code="Region" /></form:label>
            <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
            <form:errors cssClass="text-error" path="region" />
        </div>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code" /></form:label>
            <span><form:input path="code" /></span>
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Fullname" /></form:label>
            <span><form:input path="name" /></span>
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="control-group">
            <form:label path="phonenumber"><spring:message code="Phone Number" /></form:label>
            <span><form:input path="phonenumber" /></span>
            <form:errors cssClass="text-error" path="phonenumber" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty driverAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${driverAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty driverAttribute.id }">
    <spring:url var="deleteUrl" value="/driver/delete?id=${driverAttribute.id}" />
    <form:form id="form-${driverAttribute.id}" modelAttribute="driverAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />