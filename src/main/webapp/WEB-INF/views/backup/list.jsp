<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="backup_take" value="/backup/take"/>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Roles" /></li>
    <li class="pull-right">
        <a class="btn btn-mini" href="${backup_take}"><spring:message code="Take Backup" /></a>
    </li>
</ul>

<table class="table">
    <thead>
        <tr>
            <th><spring:message code="Backup File" /></th>
            <th><spring:message code="Action" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="file" items="${files}">
            <spring:url var="backup_restore" value="/backup/{filename}/restore">
                <spring:param name="filename" value="${file.name}" />
            </spring:url>
            <spring:url var="backup_delete" value="/backup/{filename}/delete">
                <spring:param name="filename" value="${file.name}" />
            </spring:url>
            <tr>
                <td>${file.name}</td>
                <td>
                    <a href="${backup_restore}"><spring:message code="Restore" /></a>
                    <a href="${backup_delete}"><spring:message code="Delete" /></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/views/footer.jsp" />