<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${forwardingList}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class=""><a href="#"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${forwardingExport}"><spring:message code="Waybill" text="Waybill"></spring:message></a></li>
			<li><a href="${uatfExport}"><spring:message code="UATF" text="UATF"></spring:message></a></li>
		</ul>
	</li>
</ul>

<aripd:datatables datasource="/forwarding/get" id="forwardings" dataUrlShow="/forwarding/show" dataUrlEdit="/forwarding/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Account" field="account.username"/>
	<aripd:column label="Waybill No" field="waybillNo"/>
	<aripd:column label="Driver" field="driver"/>
	<aripd:column label="Subcontractor" field="subcontractor.name"/>
	<aripd:column label="Quota" field="quota.name"/>
	<aripd:column label="Plate" field="plate"/>
	<aripd:column label="Starting Time" field="startingTime"/>
	<aripd:column label="Ending Time" field="endingTime"/>
	<aripd:column label="Ending Point" field="endingPoint"/>
	<aripd:column label="Weight" field="loadWeightInTonne"/>
	<aripd:column label="Shipping Cost" field="shippingCost"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>