<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="backup_take" value="/backup/take"/>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Roles"></spring:message></li>
        <li class="pull-right">
            <a class="btn btn-mini" href="${backup_take}"><spring:message code="Take Backup"></spring:message></a>
        </li>
    </ul>

    <table class="table">
        <thead>
            <tr>
                <th><spring:message code="Backup File"></spring:message></th>
            <th><spring:message code="Action"></spring:message></th>
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
                    <a href="${backup_restore}"><spring:message code="Restore"></spring:message></a>
                    <a href="${backup_delete}"><spring:message code="Delete"></spring:message></a>
                    </td>
                </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/views/footer.jsp" %>