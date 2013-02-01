<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
			  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
			  <li class="active"><spring:message code="Shifts" text="Shifts"></spring:message></li>
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
				<caption><spring:message code="Shifts" text="Shifts"></spring:message></caption>
				<thead>
					<tr>
						<th><spring:message code="Id" text="Id"></spring:message></th>
						<th><spring:message code="User" text="User"></spring:message></th>
						<th><spring:message code="Started At" text="Started At"></spring:message></th>
						<th><spring:message code="Break Count" text="Break Count"></spring:message></th>
						<th><spring:message code="Break Time" text="Break Time"></spring:message></th>
						<th><spring:message code="Electricity Consumption Start" text="Electricity Consumption Start"></spring:message></th>
						<th><spring:message code="Electricity Consumption End" text="Electricity Consumption End"></spring:message></th>
						<th><spring:message code="Production in tonne" text="Production in tonne"></spring:message></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shiftAttribute}" var="shift">
						<c:url var="editUrl" value="/shift/edit/${shift.id}" />
						<tr>
							<td><c:out value="${shift.id}" /></td>
							<td><c:out value="${shift.account.name}" /></td>
							<td><spring:eval expression="shift.startedAt" /></td>
							<td><c:out value="${shift.breakCount}" /></td>
							<td><c:out value="${shift.breakTime}" /></td>
							<td><c:out value="${shift.electrictyConsumptionStart}" /></td>
							<td><c:out value="${shift.electrictyConsumptionEnd}" /></td>
							<td><c:out value="${shift.productionInTonne}" /></td>
							<td>
								<a class="btn btn-mini" href="${editUrl}">Edit</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${empty shiftAttribute}">
			<spring:message code="No records found"></spring:message>
			</c:if>
			
			<c:url var="addUrl" value="/shift/new" />
			<c:url var="exportUrl" value="/shift/export/xls" />
			<c:url var="chartUrl" value="/shift/chart" />
			<div class="form-actions">
				<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
				<a class="btn" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
				<a class="btn" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>