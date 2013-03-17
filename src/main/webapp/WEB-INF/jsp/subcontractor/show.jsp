<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="subcontractorList" value="/subcontractor/list" />
<spring:url var="subcontractorShow" value="/subcontractor/show/${subcontractorAttribute.id}" />
<spring:url var="subcontractorNew" value="/subcontractor/new" />
<spring:url var="subcontractorEdit" value="/subcontractor/edit/${subcontractorAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${subcontractorList}"><spring:message code="Subcontractors"></spring:message></a></li>
	<li class="active"><a href="${subcontractorShow}"><spring:message code="Entry No"></spring:message>: ${subcontractorAttribute.id}</a></li>
	<li class=""><a href="${subcontractorNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:description id="subcontractors">
	<aripd:descriptionitem label="Region" field="${subcontractorAttribute.region.name}"></aripd:descriptionitem>
	<aripd:descriptionitem label="Name" field="${subcontractorAttribute.name}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
	<a class="btn" href="${subcontractorEdit}"><spring:message code="Edit"></spring:message></a>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>