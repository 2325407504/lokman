<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="truckList" value="/truck/list" />
<spring:url var="truckShow" value="/truck/show/${truckAttribute.id}" />
<spring:url var="truckNew" value="/truck/new" />
<spring:url var="truckEdit" value="/truck/edit/${truckAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${truckList}"><spring:message code="Trucks" /></a></li>
    <li class="active"><a href="${truckShow}"><spring:message code="Entry No" />: ${truckAttribute.id}</a></li>
    <li class=""><a href="${truckNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="truck">
    <aripd:descriptionitem label="Region" field="truckAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Plate" field="truckAttribute.plate"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${truckEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />