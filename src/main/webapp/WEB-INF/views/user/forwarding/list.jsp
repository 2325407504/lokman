<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/user/forwarding/list" />
<spring:url var="forwardingNew" value="/user/forwarding/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/user/forwarding/get" id="forwardings" dataUrlShow="/user/forwarding/show" dataUrlEdit="/user/forwarding/edit">
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

<%@ include file="/WEB-INF/views/footer.jsp" %>