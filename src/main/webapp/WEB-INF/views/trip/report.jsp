<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="trip" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<p class="lead">Plakaya göre</p>
<c:forEach var="truck" items="${trucks}">
    <spring:url var="tripReport" value="/trip/report/truck/{id}/xls">
        <spring:param name="id" value="${truck.id}" />
    </spring:url>
    <a class="label" href="${tripReport}">${truck.plate}</a>
</c:forEach>

<jsp:include page="/WEB-INF/views/footer.jsp" />