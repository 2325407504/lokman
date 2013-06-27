<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="machineList" value="/machine/list" />
<spring:url var="machineNew" value="/machine/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${machineList}"><spring:message code="Machines" /></a></li>
	<li class=""><a href="${machineNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/machine/get" id="machines" dataUrlShow="/machine/show" dataUrlEdit="/machine/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>