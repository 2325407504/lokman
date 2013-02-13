<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/trip/list" var="trip_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${trip_list}"><spring:message code="Trip Tracking"></spring:message></a> <span class="divider">/</span></li>
	<li class="active"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</li>
	<li class="pull-right">
		<spring:url var="editUrl" value="/trip/edit/${tripAttribute.id}" />
		<a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
	</li>
</ul>

<ul class="unstyled">
	<li>
		<label class="label">
			<spring:message code="Id" text="Id"></spring:message>
		</label>
		${tripAttribute.id}
	</li>
	<li>
		<label class="label">
			<spring:message code="Truck" text="Truck"></spring:message>
		</label>
		${tripAttribute.truck.plate}
	</li>
	<li>
		<label class="label">
			<spring:message code="Driver" text="Driver"></spring:message>
		</label>
		${tripAttribute.driver.fullname}
	</li>
	<li>
		<label class="label">
			<spring:message code="Starting Point" text="Starting Point"></spring:message>
		</label>
		${tripAttribute.startingPoint}
	</li>
	<li>
		<label class="label">
			<spring:message code="Starting km" text="Starting km"></spring:message>
		</label>
		${tripAttribute.startingKm}
	</li>
	<li>
		<label class="label">
			<spring:message code="Starting Time" text="Starting Time"></spring:message>
		</label>
		<spring:eval expression="tripAttribute.startingTime" />
	</li>
	<li>
		<label class="label">
			<spring:message code="Ending Point" text="Ending Point"></spring:message>
		</label>
		${tripAttribute.endingPoint}
	</li>
	<li>
		<label class="label">
			<spring:message code="Ending km" text="Ending km"></spring:message>
		</label>
		${tripAttribute.endingKm}
	</li>
	<li>
		<label class="label">
			<spring:message code="Ending Time" text="Ending Time"></spring:message>
		</label>
		<spring:eval expression="tripAttribute.endingTime" />
	</li>
	<li>
		<label class="label">
			<spring:message code="loadWeightInTonne" text="loadWeightInTonne"></spring:message>
		</label>
		${tripAttribute.loadWeightInTonne}
	</li>
	<li>
		<label class="label">
			<spring:message code="Remark" text="Remark"></spring:message>
		</label>
		${tripAttribute.remark}
	</li>
</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>