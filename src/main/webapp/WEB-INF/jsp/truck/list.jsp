<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Trucks"></spring:message></li>
</ul>

<spring:url var="addUrl" value="/truck/new" />
<div class="form-actions">
	<a class="btn" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
</div>

<table class="table">
	<caption><spring:message code="Trucks"></spring:message></caption>
	<thead>
		<tr>
			<th><spring:message code="Plate"></spring:message></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${truckAttribute}" var="truck">
			<spring:url var="editUrl" value="/truck/edit/${truck.id}" />
			<tr>
				<td><c:out value="${truck.plate}" /></td>
				<td>
					<a class="btn btn-mini" href="${editUrl}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:if test="${empty truckAttribute}">
<spring:message code="No records found"></spring:message>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>