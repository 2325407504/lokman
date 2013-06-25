<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="driverList" value="/driver/list" />
<spring:url var="driverEdit" value="/driver/edit/${driverAttribute.id}" />
<spring:url var="driverNew" value="/driver/new" />
<spring:url var="driverSave" value="/driver/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${driverList}"><spring:message code="Drivers"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty driverAttribute.id }">
            <li class="active"><a href="${driverEdit}"><spring:message code="Entry No"></spring:message>: ${driverAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${driverNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<form:form modelAttribute="driverAttribute" action="${driverSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="region"><spring:message code="Region"></spring:message></form:label>
            <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
            <form:errors cssClass="text-error" path="region" />
        </div>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code"></spring:message></form:label>
            <span><form:input path="code" /></span>
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Fullname"></spring:message></form:label>
            <span><form:input path="name" /></span>
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="control-group">
            <form:label path="phonenumber"><spring:message code="Phone Number"></spring:message></form:label>
            <span><form:input path="phonenumber" /></span>
            <form:errors cssClass="text-error" path="phonenumber" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty driverAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${driverAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty driverAttribute.id }">
    <spring:url var="deleteUrl" value="/driver/delete?id=${driverAttribute.id}" />
    <form:form id="form-${driverAttribute.id}" modelAttribute="driverAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>