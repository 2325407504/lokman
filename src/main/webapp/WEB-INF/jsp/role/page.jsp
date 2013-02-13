<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></li>
</ul>

<spring:url var="firstUrl" value="/role/page-1" />
<spring:url var="lastUrl" value="/role/page-${roleAttribute.totalPages}" />
<spring:url var="prevUrl" value="/role/page-${currentIndex - 1}" />
<spring:url var="nextUrl" value="/role/page-${currentIndex + 1}" />

<table class="table">
	<caption><spring:message code="Roles"></spring:message></caption>
	<thead>
		<tr>
			<th><spring:message code="Id"></spring:message></th>
			<th><spring:message code="Code"></spring:message></th>
			<th><spring:message code="label.name"></spring:message></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${roleAttribute.content}" var="role">
			<spring:url var="editUrl" value="/role/edit/${role.id}" />
			<tr>
				<td><c:out value="${role.id}" /></td>
				<td><c:out value="${role.code}" /></td>
				<td><c:out value="${role.name}" /></td>
				<td>
					<a class="btn btn-mini" href="${editUrl}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:if test="${empty roleAttribute}">
<spring:message code="No records found"></spring:message>
</c:if>

<div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <spring:url var="pageUrl" value="/role/page-${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == roleAttribute.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>