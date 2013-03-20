<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="driverList" value="/driver/list" />
<spring:url var="driverShow" value="/driver/show/${driverAttribute.id}" />
<spring:url var="driverNew" value="/driver/new" />
<spring:url var="driverEdit" value="/driver/edit/${driverAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${driverList}"><spring:message code="Drivers"></spring:message></a></li>
	<li class="active"><a href="${driverShow}"><spring:message code="Entry No"></spring:message>: ${driverAttribute.id}</a></li>
	<li class=""><a href="${driverNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:description id="drivers">
	<aripd:descriptionitem label="Region" field="${driverAttribute.region.name}"></aripd:descriptionitem>
	<aripd:descriptionitem label="Code" field="${driverAttribute.code}"></aripd:descriptionitem>
	<aripd:descriptionitem label="Fullname" field="${driverAttribute.name}"></aripd:descriptionitem>
	<aripd:descriptionitem label="Phone Number" field="${driverAttribute.phonenumber}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
	<a class="btn" href="${driverEdit}"><spring:message code="Edit"></spring:message></a>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>