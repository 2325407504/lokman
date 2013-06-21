<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="productList" value="/product/list" />
<spring:url var="productEdit" value="/product/edit/${productAttribute.id}" />
<spring:url var="productNew" value="/product/new" />
<spring:url var="productSave" value="/product/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productList}"><spring:message code="Products"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty productAttribute.id }">
            <li class="active"><a href="${productEdit}"><spring:message code="Entry No"></spring:message>: ${productAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${productNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<form:form modelAttribute="productAttribute" action="${productSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="productgroup"><spring:message code="Product Group"></spring:message></form:label>
            <form:select path="productgroup.id" multiple="false" items="${productgroups}" itemLabel="name" itemValue="id"/>
            <form:errors cssClass="text-error" path="productgroup" />
        </div>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code"></spring:message></form:label>
            <span><form:input path="code" /></span>
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name"></spring:message></form:label>
            <span><form:input path="name" /></span>
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty productAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${productAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty productAttribute.id }">
    <spring:url var="deleteUrl" value="/product/delete?id=${productAttribute.id}" />
    <form:form id="form-${productAttribute.id}" modelAttribute="productAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>