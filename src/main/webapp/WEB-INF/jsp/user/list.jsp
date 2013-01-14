<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="label.users"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="label.users"></spring:message></caption>
			<thead>
				<tr>
					<th><spring:message code="label.username"></spring:message></th>
					<th><spring:message code="label.firstname"></spring:message></th>
					<th><spring:message code="label.lastname"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${entities}" var="user">
					<c:url var="editUrl" value="/user/edit/${user.id}" />
					<c:url var="deleteUrl" value="/user/delete?id=${user.id}" />
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
							<a class="btn btn-mini" href="${deleteUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty entities}">
		No records found. 
		</c:if>

		<c:url var="addUrl" value="/user/new"/>
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="button.new"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>