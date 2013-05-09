<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripShow" value="/trip/show/${tripAttribute.id}" />
<spring:url var="tripEdit" value="/trip/edit/${tripAttribute.id}" />
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripSubmit" value="/trip/submit/${tripAttribute.id}" />
<spring:url var="tripExport" value="/trip/export/xls" />
<spring:url var="tripChart" value="/trip/chart" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
	<li class="active"><a href="${tripShow}"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
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

<c:if test="${tripAttribute.submitted}">
<div class="alert alert-error">
	<spring:message code="Submitted by user"></spring:message>
	<a href="${tripEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${tripSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
</div>
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
					<spring:message code="Account"></spring:message>
				</label>
				${tripAttribute.account.customer.fullname}
			</li>
			<li>
				<label class="label">
					<spring:message code="Truck"></spring:message>
				</label>
				${tripAttribute.truck.plate}
			</li>
			<li>
				<label class="label">
					<spring:message code="Driver"></spring:message>
				</label>
				${tripAttribute.driver.name}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Point"></spring:message>
				</label>
				${tripAttribute.startingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Km"></spring:message>
				</label>
				${tripAttribute.startingKm}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Time"></spring:message>
				</label>
				<spring:eval expression="tripAttribute.startingTime" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Point"></spring:message>
				</label>
				${tripAttribute.endingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Km"></spring:message>
				</label>
				${tripAttribute.endingKm}
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Time"></spring:message>
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
					<spring:message code="Remark"></spring:message>
				</label>
				${tripAttribute.remark}
			</li>
		</ul>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>