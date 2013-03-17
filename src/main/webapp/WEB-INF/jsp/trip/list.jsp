<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripExport" value="/trip/export/xls" />
<spring:url var="tripChart" value="/trip/chart" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${tripList}"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class=""><a href="#"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${tripExport}"><spring:message code="Export"></spring:message></a></li>
			<li><a href="${tripChart}"><spring:message code="Chart"></spring:message></a></li>
		</ul>
	</li>
</ul>

<aripd:datatables datasource="/trip/get" id="trips" dataUrlShow="/trip/show" dataUrlEdit="/trip/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Account" field="account.username"/>
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