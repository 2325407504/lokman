<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></li>
	<spring:url var="addUrl" value="/trip/new" />
	<spring:url var="exportUrl" value="/trip/export/xls" />
	<spring:url var="chartUrl" value="/trip/chart" />
	<li class="pull-right">
		<a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
		<a class="btn btn-mini" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
		<a class="btn btn-mini" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
	</li>
</ul>

<aripd:datatables datasource="/trip/get" id="trips" dataUrlShow="/trip/show" dataUrlEdit="/trip/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Truck" field="truck.plate"/>
	<aripd:column label="Driver" field="driver.fullname"/>
	<aripd:column label="Starting Point" field="startingPoint"/>
	<aripd:column label="Starting Time" field="startingTime"/>
	<aripd:column label="Starting km" field="startingKm"/>
	<aripd:column label="Ending Point" field="endingPoint"/>
	<aripd:column label="Ending Time" field="endingTime"/>
	<aripd:column label="Ending km" field="endingKm"/>
	<aripd:column label="Weight" field="loadWeightInTonne"/>
	<aripd:column label="Remark" field="remark"/>
</aripd:datatables>
			
<%@ include file="/WEB-INF/jsp/footer.jsp" %>