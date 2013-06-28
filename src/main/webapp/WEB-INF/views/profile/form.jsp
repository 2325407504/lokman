<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Profile" /></li>
    </ul>
<spring:url var="saveUrl" value="/profile/save" />
<form:form modelAttribute="profileAttribute" action="${saveUrl}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <fieldset>
        <div class="control-group">
            <form:label path="client.firstName"><spring:message code="FirstName" /></form:label>
            <span><form:input path="client.firstName" /></span>
        </div>       
        <div class="control-group">
            <form:label path="client.lastName"><spring:message code="LastName" /></form:label>
            <span><form:input path="client.lastName" /></span>
        </div>
        <div class="control-group">
            <form:label path="email"><spring:message code="E-mail Address" /></form:label>
            <span><form:input path="email" /></span>
            <form:errors cssClass="text-error" path="email" />
        </div>
        <div class="control-group">
            <form:label path="username"><spring:message code="Username" /></form:label>
            <span><form:input path="username" /></span>
            <form:errors cssClass="text-error" path="username" />
        </div>
        <div class="control-group">
            <form:label path="password"><spring:message code="New Password" /></form:label>
            <span><form:password path="password" /></span>
            <form:errors cssClass="text-error" path="password" />
        </div>
        <div class="form-actions">
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp" />