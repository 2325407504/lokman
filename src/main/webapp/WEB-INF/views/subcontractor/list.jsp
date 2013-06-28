<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="subcontractorList" value="/subcontractor/list" />
<spring:url var="subcontractorNew" value="/subcontractor/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${subcontractorList}"><spring:message code="Subcontractors" /></a></li>
	<li class=""><a href="${subcontractorNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/subcontractor/get" id="subcontractors" dataUrlShow="/subcontractor/show" dataUrlEdit="/subcontractor/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Region" field="region.name"/>
	<aripd:column label="Code" field="code"/>
	<aripd:column label="Subcontractor" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />