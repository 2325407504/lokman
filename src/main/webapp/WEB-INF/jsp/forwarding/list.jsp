<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></li>
	<li class="pull-right">
		<spring:url var="addUrl" value="/forwarding/new" />
		<spring:url var="exportUrl" value="/forwarding/export/xls" />
		<spring:url var="uatfExportUrl" value="/uatf/export/xls" />
		<a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
		<div class="btn-group">
			<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">
				<spring:message code="Export"></spring:message>
				<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="${exportUrl}"><spring:message code="Waybill" text="Waybill"></spring:message></a></li>
				<li><a href="${uatfExportUrl}"><spring:message code="UATF" text="UATF"></spring:message></a></li>
			</ul>
		</div>
	</li>
</ul>

<aripd:datatables datasource="/forwarding/get" id="forwardings" dataUrlShow="/forwarding/show" dataUrlEdit="/forwarding/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Waybill No" field="waybillNo"/>
	<aripd:column label="Driver" field="driver"/>
	<aripd:column label="Subcontractor" field="subcontractor.name"/>
	<aripd:column label="Quota" field="quota.name"/>
	<aripd:column label="Plate" field="plate"/>
	<aripd:column label="Starting At" field="startingAt"/>
	<aripd:column label="Ending At" field="endingAt"/>
	<aripd:column label="Ending Point" field="endingPoint"/>
	<aripd:column label="Weight" field="loadWeightInTonne"/>
	<aripd:column label="Shipping Cost" field="shippingCost"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>