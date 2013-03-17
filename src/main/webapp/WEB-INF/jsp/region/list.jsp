<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="regionList" value="/region/list" />
<spring:url var="regionNew" value="/region/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${regionList}"><spring:message code="Regions"></spring:message></a></li>
	<li class=""><a href="${regionNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/region/get" id="regions" dataUrlShow="/region/show" dataUrlEdit="/region/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>