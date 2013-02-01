<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="label.roles"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="label.roles"></spring:message></caption>
			<thead>
				<tr>
					<th><spring:message code="Id"></spring:message></th>
					<th><spring:message code="Code"></spring:message></th>
					<th><spring:message code="label.name"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleAttribute}" var="role">
					<c:url var="editUrl" value="/role/edit/${role.id}" />
					<tr>
						<td><c:out value="${role.id}" /></td>
						<td><c:out value="${role.code}" /></td>
						<td><c:out value="${role.name}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty roleAttribute}">
		<spring:message code="No records found"></spring:message>
		</c:if>

		<c:url var="addUrl" value="/role/new"/>
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="New entry"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>