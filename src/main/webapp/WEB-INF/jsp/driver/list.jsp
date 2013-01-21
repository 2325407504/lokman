<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Drivers"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="Drivers"></spring:message></caption>
			<thead>
				<tr>
					<th><spring:message code="Firstname"></spring:message></th>
					<th><spring:message code="Lastname"></spring:message></th>
					<th><spring:message code="Phone Number"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${driverAttribute}" var="driver">
					<c:url var="editUrl" value="/driver/edit/${driver.id}" />
					<tr>
						<td><c:out value="${driver.firstname}" /></td>
						<td><c:out value="${driver.lastname}" /></td>
						<td><c:out value="${driver.phonenumber}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty driverAttribute}">
		No records found. 
		</c:if>
		
		<c:url var="addUrl" value="/driver/new" />
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="button.new"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>