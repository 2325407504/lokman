<%@ include file="/WEB-INF/views/header.jsp" %>

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
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Truck" field="truck.plate"/>
    <aripd:column label="Driver" field="driver.name"/>
    <aripd:column label="Starting Point" field="startingPoint"/>
    <aripd:column label="Starting Time" field="startingTime"/>
    <aripd:column label="Starting Km" field="startingKm"/>
    <aripd:column label="Ending Point" field="endingPoint"/>
    <aripd:column label="Ending Time" field="endingTime"/>
    <aripd:column label="Ending Km" field="endingKm"/>
    <aripd:column label="Weight" field="loadWeightInTonne"/>
    <aripd:column label="Remark" field="remark"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>