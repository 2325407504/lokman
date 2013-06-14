<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Profile"></spring:message></li>
    </ul>
<spring:url var="saveUrl" value="/profile/save" />
<form:form modelAttribute="profileAttribute" action="${saveUrl}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <fieldset>
        <div class="control-group">
            <form:label path="customer.firstName"><spring:message code="FirstName"></spring:message></form:label>
            <span><form:input path="customer.firstName" /></span>
        </div>       
        <div class="control-group">
            <form:label path="customer.lastName"><spring:message code="LastName"></spring:message></form:label>
            <span><form:input path="customer.lastName" /></span>
        </div>
        <div class="control-group">
            <form:label path="email"><spring:message code="E-mail Address"></spring:message></form:label>
            <span><form:input path="email" /></span>
            <form:errors cssClass="text-error" path="email" />
        </div>
        <div class="control-group">
            <form:label path="username"><spring:message code="Username"></spring:message></form:label>
            <span><form:input path="username" /></span>
            <form:errors cssClass="text-error" path="username" />
        </div>
        <div class="control-group">
            <form:label path="password"><spring:message code="New Password"></spring:message></form:label>
            <span><form:password path="password" /></span>
            <form:errors cssClass="text-error" path="password" />
        </div>
        <div class="form-actions">
            <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
            </div>
        </fieldset>
</form:form>

<%@ include file="/WEB-INF/views/footer.jsp" %>