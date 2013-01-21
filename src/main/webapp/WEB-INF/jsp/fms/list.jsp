<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Field Management System"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="Field Management System"></spring:message></caption>
			<thead>
				<tr>
					<th><spring:message code="Id" text="Id"></spring:message></th>
					<th><spring:message code="Datetime" text="Datetime"></spring:message></th>
					<th><spring:message code="Starting km" text="Starting km"></spring:message></th>
					<th><spring:message code="Ending km" text="Ending km"></spring:message></th>
					<th><spring:message code="Fuel consumption in TL" text="Fuel consumption in TL"></spring:message></th>
					<th><spring:message code="Fuel consumption in liter" text="Fuel consumption in liter"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${fmsAttribute}" var="fms">
					<c:url var="editUrl" value="/fms/edit/${fms.id}" />
					<tr>
						<td><c:out value="${fms.id}" /></td>
						<td><spring:eval expression="fms.createdAt" /></td>
						<td><c:out value="${fms.startingKm}" /></td>
						<td><c:out value="${fms.endingKm}" /></td>
						<td><c:out value="${fms.fuelTL}" /></td>
						<td><c:out value="${fms.fuelLiter}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty fmsAttribute}">
		No records found. 
		</c:if>
		
		<c:url var="addUrl" value="/fms/new" />
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="button.new"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>