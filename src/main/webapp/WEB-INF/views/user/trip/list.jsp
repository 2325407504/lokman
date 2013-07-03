<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/user/trip/list" />
<spring:url var="tripNew" value="/user/trip/new" />
<spring:url var="tripReport" value="/user/trip/report" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${tripList}"><spring:message code="Trips" /></a></li>
    <li class=""><a href="${tripNew}"><spring:message code="New Entry" /></a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Reports" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${tripReport}"><spring:message code="Report" /></a></li>
        </ul>
    </li>
</ul>

<aripd:datatables datasource="/user/trip/get" id="trips" dataUrlShow="/user/trip/show" dataUrlEdit="/user/trip/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.username"/>
    <aripd:datatablescolumn label="Truck" field="truck.plate"/>
    <aripd:datatablescolumn label="Driver" field="driver.name"/>
    <aripd:datatablescolumn label="Starting Point" field="startingPoint"/>
    <aripd:datatablescolumn label="Starting Time" field="startingTime"/>
    <aripd:datatablescolumn label="Starting Km" field="startingKm"/>
    <aripd:datatablescolumn label="Ending Point" field="endingPoint"/>
    <aripd:datatablescolumn label="Ending Time" field="endingTime"/>
    <aripd:datatablescolumn label="Ending Km" field="endingKm"/>
    <aripd:datatablescolumn label="Weight" field="loadWeightInTonne"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />