<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripShow" value="/trip/show/{id}">
    <spring:param name="id" value="${tripAttribute.id}" />
</spring:url>
<spring:url var="tripEdit" value="/trip/edit/{id}">
    <spring:param name="id" value="${tripAttribute.id}" />
</spring:url>
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripSubmit" value="/trip/submit/{id}">
    <spring:param name="id" value="${tripAttribute.id}" />
</spring:url>
<spring:url var="tripExport" value="/trip/export/xls" />
<spring:url var="tripChart" value="/trip/chart" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${tripList}"><spring:message code="Trips" /></a></li>
    <li class="active"><a href="${tripShow}"><spring:message code="Entry No" />: ${tripAttribute.id}</a></li>
    <li class=""><a href="${tripNew}"><spring:message code="New Entry" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${tripExport}"><spring:message code="Export" /></a></li>
            <li><a href="${tripChart}"><spring:message code="Chart" /></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${tripAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${tripSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
        </div>
</c:if>
<c:if test="${!tripAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${tripSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="trip">
            <aripd:descriptionitem label="Status" field="tripAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="tripAttribute.account.client.fullname"></aripd:descriptionitem>
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

<jsp:include page="/WEB-INF/views/footer.jsp" />