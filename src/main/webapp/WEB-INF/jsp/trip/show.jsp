<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripShow" value="/trip/show/${tripAttribute.id}" />
<spring:url var="tripEdit" value="/trip/edit/${tripAttribute.id}" />
<spring:url var="tripNew" value="/trip/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></a></li>
	<li class="active"><a href="${tripShow}"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<c:if test="${tripAttribute.submitted}">
<div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
</c:if>
<c:if test="${!tripAttribute.submitted}">
<div class="alert alert-info"><a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a></div>
</c:if>

<div class="row-fluid">
	<div class="span12">
		<ul class="unstyled">
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
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>