<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="wasteList" value="/waste/list" />
<spring:url var="wasteShow" value="/waste/show/${wasteAttribute.id}" />
<spring:url var="wasteNew" value="/waste/new" />
<spring:url var="wasteEdit" value="/waste/edit/${wasteAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${wasteList}"><spring:message code="Wastes" /></a></li>
    <li class="active"><a href="${wasteShow}"><spring:message code="Entry No" />: ${wasteAttribute.id}</a></li>
    <li class=""><a href="${wasteNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="waste">
    <aripd:descriptionitem label="Waste Group" field="wasteAttribute.wastegroup.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="wasteAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="wasteAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${wasteEdit}"><spring:message code="Edit" /></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>