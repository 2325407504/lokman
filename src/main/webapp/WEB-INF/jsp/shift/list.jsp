<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Shifts" text="Shifts"></spring:message></li>
</ul>

<table class="table">
	<caption><spring:message code="Shifts" text="Shifts"></spring:message></caption>
	<thead>
		<tr>
			<th><spring:message code="Id" text="Id"></spring:message></th>
			<th><spring:message code="Account"></spring:message></th>
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
			<spring:url var="editUrl" value="/shift/edit/${shift.id}" />
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

<spring:url var="addUrl" value="/shift/new" />
<spring:url var="exportUrl" value="/shift/export/xls" />
<spring:url var="chartUrl" value="/shift/chart" />
<div class="form-actions">
	<a class="btn" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
	<a class="btn" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
	<a class="btn" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>