<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/user/trip/list" />
<spring:url var="tripShow" value="/user/trip/show/${tripAttribute.id}" />
<spring:url var="tripEdit" value="/user/trip/edit/${tripAttribute.id}" />
<spring:url var="tripNew" value="/user/trip/new" />
<spring:url var="tripSubmit" value="/user/trip/submit/${tripAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
    <li class="active"><a href="${tripShow}"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</a></li>
    <li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
    </ul>

<c:if test="${tripAttribute.submitted}">
    <div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
</c:if>
<c:if test="${!tripAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${tripSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="trip">
            <aripd:descriptionitem label="Truck" field="tripAttribute.truck.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="tripAttribute.driver.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Point" field="tripAttribute.startingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Km" field="tripAttribute.startingKm"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="tripAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="tripAttribute.endingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Km" field="tripAttribute.endingKm"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="tripAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="tripAttribute.loadWeightInTonne"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="tripAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>