<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
			  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
			  <li class="active"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="span12">
		
			<table class="table">
				<caption><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></caption>
				<thead>
					<tr>
						<th><spring:message code="Id" text="Id"></spring:message></th>
						<th><spring:message code="Date" text="Date"></spring:message></th>
						<th><spring:message code="Truck" text="Truck"></spring:message></th>
						<th><spring:message code="Driver" text="Driver"></spring:message></th>
						<th><spring:message code="Starting km" text="Starting km"></spring:message></th>
						<th><spring:message code="Ending km" text="Ending km"></spring:message></th>
						<th><spring:message code="loadWeightInTonne" text="loadWeightInTonne"></spring:message></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tripAttribute}" var="trip">
						<c:url var="editUrl" value="/trip/edit/${trip.id}" />
						<tr>
							<td><c:out value="${trip.id}" /></td>
							<td><spring:eval expression="trip.publishedAt" /></td>
							<td><c:out value="${trip.truck.plate}" /></td>
							<td><c:out value="${trip.driver.fullname}" /></td>
							<td><c:out value="${trip.startingKm}" /></td>
							<td><c:out value="${trip.endingKm}" /></td>
							<td><c:out value="${trip.loadWeightInTonne}" /></td>
							<td>
								<a class="btn btn-mini" href="${editUrl}">Edit</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${empty tripAttribute}">
			<spring:message code="No records found"></spring:message>
			</c:if>
			
			<c:url var="addUrl" value="/trip/new" />
			<c:url var="exportUrl" value="/trip/export/xls" />
			<c:url var="chartUrl" value="/trip/chart" />
			<div class="form-actions">
				<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
				<a class="btn" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
				<a class="btn" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>