<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/account/list" var="account_list" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li><a href="${account_list}"><spring:message code="Accounts" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="New Entry" /></li>
    </ul>
<spring:url var="saveUrl" value="/account/save" />
<form:form modelAttribute="accountAttribute" action="${saveUrl}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <form:hidden path="client.id" />
    <fieldset>
        <div class="control-group">
            <form:label path="active">Aktif mi?</form:label>
            <form:checkbox path="active" />
            <form:errors cssClass="text-error" path="active" />
        </div>
        <div class="control-group">
            <form:label path="region"><spring:message code="Region" /></form:label>
            <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
            <form:errors cssClass="text-error" path="region" />
        </div>
        <div class="control-group">
            <form:label path="client.firstName"><spring:message code="FirstName" /></form:label>
            <form:input path="client.firstName" />
            <form:errors cssClass="text-error" path="client.firstName" />
        </div>       
        <div class="control-group">
            <form:label path="client.lastName"><spring:message code="LastName" /></form:label>
            <form:input path="client.lastName" />
            <form:errors cssClass="text-error" path="client.lastName" />
        </div>
        <div class="control-group">
            <form:label path="email"><spring:message code="E-mail Address" /></form:label>
            <form:input path="email" />
            <form:errors cssClass="text-error" path="email" />
        </div>
        <div class="control-group">
            <form:label path="username"><spring:message code="Username" /></form:label>
            <form:input path="username" />
            <form:errors cssClass="text-error" path="username" />
        </div>
        <div class="control-group">
            <form:label path="password"><spring:message code="Password" /></form:label>
            <form:password path="password" />
            <form:errors cssClass="text-error" path="password" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty accountAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${accountAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty accountAttribute.id }">
    <spring:url var="deleteUrl" value="/account/delete?id=${accountAttribute.id}" />
    <form:form id="form-${accountAttribute.id}" modelAttribute="accountAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>