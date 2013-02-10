<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
			  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
			  <li class="active"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></li>
			</ul>
		
			<spring:url var="addUrl" value="/trip/new" />
			<spring:url var="exportUrl" value="/trip/export/xls" />
			<spring:url var="chartUrl" value="/trip/chart" />
			
			<div class="form-actions">
				<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
				<a class="btn" href="${exportUrl}"><spring:message code="Export"></spring:message></a>
				<a class="btn" href="${chartUrl}"><spring:message code="Chart"></spring:message></a>
			</div>
			
			<datatables:table datasource="/trip/get" id="trips" dataUrlEdit="/trip/edit">
				<datatables:column label="Id" field="id"></datatables:column>
				<datatables:column label="Date" field="publishedAt"></datatables:column>
				<datatables:column label="Starting Point" field="startingPoint"></datatables:column>
				<datatables:column label="Ending Point" field="endingPoint"></datatables:column>
				<datatables:column label="Starting Time" field="startingTime"></datatables:column>
				<datatables:column label="Ending Time" field="endingTime"></datatables:column>
				<datatables:column label="Starting km" field="startingKm"></datatables:column>
				<datatables:column label="Ending km" field="endingKm"></datatables:column>
				<datatables:column label="Weight" field="loadWeightInTonne"></datatables:column>
			</datatables:table>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>