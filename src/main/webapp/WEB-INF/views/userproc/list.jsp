<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Procedures" /></li>
</ul>

<c:forEach var="proc" items="${userprocs}">
    <spring:url var="userproc_show" value="/userproc/show/{id}">
        <spring:param name="id" value="${proc.id}" />
    </spring:url>
    <div><a href="${userproc_show}">${proc.name}</a></div>
</c:forEach>

<jsp:include page="/WEB-INF/views/footer.jsp" />