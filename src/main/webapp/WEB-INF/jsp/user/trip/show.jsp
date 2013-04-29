<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/user/trip/list" />
<spring:url var="tripShow" value="/user/trip/show/${tripAttribute.id}" />
<spring:url var="tripEdit" value="/user/trip/edit/${tripAttribute.id}" />
<spring:url var="tripNew" value="/user/trip/new" />
<spring:url var="tripSubmit" value="/user/trip/submit/${tripAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
	<li class="active"><a href="${tripShow}"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<c:if test="${tripAttribute.submitted}">
<div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
</c:if>
<c:if test="${!tripAttribute.submitted}">
<div class="alert alert-info">
	<a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${tripSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
</div>
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
				${tripAttribute.driver.name}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Point" text="Starting Point"></spring:message>
				</label>
				${tripAttribute.startingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Km" text="Starting Km"></spring:message>
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
					<spring:message code="Ending Km" text="Ending Km"></spring:message>
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
					<spring:message code="Weight"></spring:message>
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