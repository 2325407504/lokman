<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Procedures" /></li>
</ul>

<ul>
    <c:forEach var="proc" items="${userprocs}">
        <spring:url var="userproc_show" value="/userproc/show/{id}">
            <spring:param name="id" value="${proc.id}" />
        </spring:url>
        <li>
            <a href="${userproc_show}">${proc.name}</a>
        </li>
    </c:forEach>
</ul>

<jsp:include page="/WEB-INF/views/footer.jsp" />