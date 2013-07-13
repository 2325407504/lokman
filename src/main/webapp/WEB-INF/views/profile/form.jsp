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
            <form:label path="employee.firstName"><spring:message code="FirstName" /></form:label>
            <form:input path="employee.firstName" />
        </div>       
        <div class="control-group">
            <form:label path="employee.lastName"><spring:message code="LastName" /></form:label>
            <form:input path="employee.lastName" />
        </div>
        <div class="control-group">
            <form:label path="employee.birthdate"><spring:message code="Date of birth" /></form:label>
            <form:input path="employee.birthdate" />
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
            <form:label path="password"><spring:message code="New Password" /></form:label>
            <form:password path="password" />
            <form:errors cssClass="text-error" path="password" />
        </div>
        <div class="form-actions">
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp" />