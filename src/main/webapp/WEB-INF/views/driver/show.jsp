<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="driverList" value="/driver/list" />
<spring:url var="driverShow" value="/driver/show/${driverAttribute.id}" />
<spring:url var="driverNew" value="/driver/new" />
<spring:url var="driverEdit" value="/driver/edit/${driverAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${driverList}"><spring:message code="Drivers" /></a></li>
    <li class="active"><a href="${driverShow}"><spring:message code="Entry No" />: ${driverAttribute.id}</a></li>
    <li class=""><a href="${driverNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="driver">
    <aripd:descriptionitem label="Region" field="driverAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="driverAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="driverAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Phone Number" field="driverAttribute.phonenumber"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${driverEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />