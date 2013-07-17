<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<p class="lead"><spring:message code="Employee Leave" /></p>
<spring:url var="employeeleaveReport" value="/employeeleave/report" />
<form:form modelAttribute="employeeleaveFilterByIntervalForm" action="${employeeleaveReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:select multiple="false" path="account.id" cssClass="input-medium" items="${accounts}" itemLabel="employee.fullname" itemValue="id" />
    <button class="btn" type="submit">
        <i class="icon-download-alt"></i> <spring:message code="Download" />
    </button>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp" />