<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/user/trip/list" />
<spring:url var="tripNew" value="/user/trip/new" />
<spring:url var="tripReport" value="/user/trip/report" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
    <li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
        <li class="active dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Reports"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${tripReport}"><spring:message code="Report"></spring:message></a></li>
            </ul>
        </li>
    </ul>

    <p class="lead">Plakaya göre</p>
<c:forEach var="truck" items="${trucks}">
    <spring:url var="tripReport" value="/user/trip/report/truck/{id}/xls">
        <spring:param name="id" value="${truck.id}" />
    </spring:url>

    <a class="label" href="${tripReport}">${truck.plate}</a>
</c:forEach>

<%@ include file="/WEB-INF/views/footer.jsp" %>