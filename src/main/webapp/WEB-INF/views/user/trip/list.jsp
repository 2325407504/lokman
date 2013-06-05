<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/user/trip/list" />
<spring:url var="tripNew" value="/user/trip/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/user/trip/get" id="trips" dataUrlShow="/user/trip/show" dataUrlEdit="/user/trip/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Account" field="account.username"/>
	<aripd:column label="Truck" field="truck.plate"/>
	<aripd:column label="Driver" field="driver.name"/>
	<aripd:column label="Starting Point" field="startingPoint"/>
	<aripd:column label="Starting Time" field="startingTime"/>
	<aripd:column label="Starting Km" field="startingKm"/>
	<aripd:column label="Ending Point" field="endingPoint"/>
	<aripd:column label="Ending Time" field="endingTime"/>
	<aripd:column label="Ending Km" field="endingKm"/>
	<aripd:column label="Weight" field="loadWeightInTonne"/>
	<aripd:column label="Remark" field="remark"/>
</aripd:datatables>
			
<%@ include file="/WEB-INF/views/footer.jsp" %>