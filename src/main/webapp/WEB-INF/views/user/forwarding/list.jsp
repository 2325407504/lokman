<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/user/forwarding/list" />
<spring:url var="forwardingNew" value="/user/forwarding/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/user/forwarding/get" id="forwardings" dataUrlShow="/user/forwarding/show" dataUrlEdit="/user/forwarding/edit">
	<aripd:datatablescolumn label="Action" field="id"/>
	<aripd:datatablescolumn label="Account" field="account.username"/>
	<aripd:datatablescolumn label="Document No" field="waybillNo"/>
	<aripd:datatablescolumn label="Driver" field="driver"/>
	<aripd:datatablescolumn label="Subcontractor" field="subcontractor.name"/>
	<aripd:datatablescolumn label="Quota" field="quota.name"/>
	<aripd:datatablescolumn label="Plate" field="plate"/>
	<aripd:datatablescolumn label="Starting Time" field="startingTime"/>
	<aripd:datatablescolumn label="Ending Time" field="endingTime"/>
	<aripd:datatablescolumn label="Ending Point" field="endingPoint"/>
	<aripd:datatablescolumn label="Weight" field="loadWeightInTonne"/>
	<aripd:datatablescolumn label="Shipping Cost" field="shippingCost"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />