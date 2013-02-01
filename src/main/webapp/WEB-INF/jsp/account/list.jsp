<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="label.users"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="label.users"></spring:message></caption>
			<thead>
				<tr>
					<th><spring:message code="Username"></spring:message></th>
					<th><spring:message code="FirstName"></spring:message></th>
					<th><spring:message code="LastName"></spring:message></th>
					<th><spring:message code="E-mail Address"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountAttribute}" var="account">
					<c:url var="editUrl" value="/account/edit/${account.id}" />
					<tr>
						<td><c:out value="${account.username}" /></td>
						<td><c:out value="${account.firstName}" /></td>
						<td><c:out value="${account.lastName}" /></td>
						<td><c:out value="${account.email}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty accountAttribute}">
		<spring:message code="No records found"></spring:message>
		</c:if>

		<c:url var="addUrl" value="/account/new"/>
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>