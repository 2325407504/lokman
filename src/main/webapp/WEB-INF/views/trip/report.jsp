<%@ include file="/WEB-INF/views/header.jsp" %>

<h1>report page</h1>
<hr>

<c:forEach var="truck" items="${trucks}">
    <spring:url var="tripReport" value="/trip/report">
        <spring:param name="id" value="${truck.id}" />
    </spring:url>

    <a class="label" href="${tripReport}">${truck.plate}</a>
</c:forEach>

<%@ include file="/WEB-INF/views/footer.jsp" %>