<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="subcontractorList" value="/subcontractor/list" />
<spring:url var="subcontractorNew" value="/subcontractor/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${subcontractorList}"><spring:message code="Subcontractors"></spring:message></a></li>
	<li class=""><a href="${subcontractorNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/subcontractor/get" id="subcontractors" dataUrlShow="/subcontractor/show" dataUrlEdit="/subcontractor/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Region" field="region.name"/>
	<aripd:column label="Subcontractor" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>