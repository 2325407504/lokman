<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="subcontractorList" value="/subcontractor/list" />
<spring:url var="subcontractorShow" value="/subcontractor/show/${subcontractorAttribute.id}" />
<spring:url var="subcontractorNew" value="/subcontractor/new" />
<spring:url var="subcontractorEdit" value="/subcontractor/edit/${subcontractorAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${subcontractorList}"><spring:message code="Subcontractors" /></a></li>
    <li class="active"><a href="${subcontractorShow}"><spring:message code="Entry No" />: ${subcontractorAttribute.id}</a></li>
    <li class=""><a href="${subcontractorNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="subcontractor">
    <aripd:descriptionitem label="Region" field="subcontractorAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="subcontractorAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="subcontractorAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${subcontractorEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />