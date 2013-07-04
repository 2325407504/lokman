<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Login Failure" />
</jsp:include>

<div class="alert alert-error text-center">
    <spring:url value="/" var="homeUrl" />
    <h1><spring:message code="Login Failure" /></h1>
    <p><a class="btn" href="${homeUrl}"><spring:message code="Login" /></a></p>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />