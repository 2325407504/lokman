<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Unauthorized" />
</jsp:include>

<div class="alert alert-error text-center">
    <h1><spring:message code="Unauthorized" /></h1>
    <p><spring:message code="Access Denied" /></p>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />