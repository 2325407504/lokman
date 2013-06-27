<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="waybillList" value="/waybill/list" />
<spring:url var="waybillNew" value="/waybill/new" />
<spring:url var="waybillImport" value="/waybill/import/xls" />
<spring:url var="waybillExport" value="/waybill/export/xls" />
<spring:url var="outgoingExport" value="/outgoing/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${waybillList}"><spring:message code="Waybills" /></a></li>
	<li class=""><a href="${waybillNew}"><spring:message code="New Entry" /></a></li>
	<li class=""><a href="${waybillImport}"><spring:message code="Import" /></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export" />
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${waybillExport}"><spring:message code="Waybill" /></a></li>
			<li><a href="${outgoingExport}"><spring:message code="Outgoing" /></a></li>
		</ul>
	</li>
</ul>

<aripd:datatables datasource="/waybill/get" id="waybills" dataUrlShow="/waybill/show" dataUrlEdit="/waybill/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Account" field="account.username"/>
	<aripd:column label="Document No" field="documentNo"/>
	<aripd:column label="Document Date" field="documentDate"/>
	<aripd:column label="Company" field="company"/>
	<aripd:column label="Driver" field="driver"/>
	<aripd:column label="Plate" field="plate"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>