<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Roles"></spring:message></li>
</ul>

<spring:url var="addUrl" value="/role/new"/>

<div class="form-actions">
	<a class="btn" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
</div>

<table class="table">
	<caption><spring:message code="Roles"></spring:message></caption>
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
			<spring:url var="editUrl" value="/role/edit/${role.id}" />
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

<%@ include file="/WEB-INF/jsp/footer.jsp" %>