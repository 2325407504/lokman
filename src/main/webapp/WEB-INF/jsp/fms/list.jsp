<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
			  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
			  <li class="active"><spring:message code="Field Management System" text="Field Management System"></spring:message></li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="span12">
		
			<!-- table id="example">
				<thead>
					<tr>
						<th>Column 1</th>
						<th>Column 2</th>
						<th>Column 3</th>
						<th>Column 4</th>
						<th>Column 5</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table-->
			
			<table class="table">
				<caption><spring:message code="Field Management System" text="Field Management System"></spring:message></caption>
				<thead>
					<tr>
						<th><spring:message code="Id" text="Id"></spring:message></th>
						<th><spring:message code="Date" text="Date"></spring:message></th>
						<th><spring:message code="Truck" text="Truck"></spring:message></th>
						<th><spring:message code="Driver" text="Driver"></spring:message></th>
						<th><spring:message code="Starting km" text="Starting km"></spring:message></th>
						<th><spring:message code="Ending km" text="Ending km"></spring:message></th>
						<th><spring:message code="Fuel consumption in TL" text="Fuel consumption in TL"></spring:message></th>
						<th><spring:message code="Fuel consumption in liter" text="Fuel consumption in liter"></spring:message></th>
						<th><spring:message code="Load Tonne" text="Load Tonne"></spring:message></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fmsAttribute}" var="fms">
						<c:url var="editUrl" value="/fms/edit/${fms.id}" />
						<tr>
							<td><c:out value="${fms.id}" /></td>
							<td><spring:eval expression="fms.publishedAt" /></td>
							<td><c:out value="${fms.truck.plate}" /></td>
							<td><c:out value="${fms.driver.fullname}" /></td>
							<td><c:out value="${fms.startingKm}" /></td>
							<td><c:out value="${fms.endingKm}" /></td>
							<td><c:out value="${fms.fuelTL}" /></td>
							<td><c:out value="${fms.fuelLiter}" /></td>
							<td><c:out value="${fms.loadTon}" /></td>
							<td>
								<a class="btn btn-mini" href="${editUrl}">Edit</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${empty fmsAttribute}">
			<spring:message code="No records found"></spring:message>
			</c:if>
			
			<c:url var="addUrl" value="/fms/new" />
			<c:url var="exportUrl" value="/fms/export/xls" />
			<c:url var="chartUrl" value="/fms/chart" />
			<div class="form-actions">
				<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
				<a class="btn" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
				<a class="btn" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>