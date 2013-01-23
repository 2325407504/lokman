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
					<th><spring:message code="Lastname"></spring:message></th>
					<th><spring:message code="E-mail Address"></spring:message></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userAttribute}" var="user">
					<c:url var="editUrl" value="/user/edit/${user.id}" />
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td>
							<a class="btn btn-mini" href="${editUrl}">Edit</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty userAttribute}">
		No records found. 
		</c:if>

		<c:url var="addUrl" value="/user/new"/>
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="button.new"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>