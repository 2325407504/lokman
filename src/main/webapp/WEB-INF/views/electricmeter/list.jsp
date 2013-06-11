<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="electricmeterList" value="/electricmeter/list" />
<spring:url var="electricmeterNew" value="/electricmeter/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${electricmeterList}"><spring:message code="Electricmeters"></spring:message></a></li>
	<li class=""><a href="${electricmeterNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/electricmeter/get" id="electricmeters" dataUrlShow="/electricmeter/show" dataUrlEdit="/electricmeter/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>