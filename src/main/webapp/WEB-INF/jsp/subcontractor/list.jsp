<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Subcontractors"></spring:message></li>
</ul>

<spring:url var="addUrl" value="/subcontractor/new" />
<div class="form-actions">
	<a class="btn" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
</div>

<table class="table">
	<caption><spring:message code="Subcontractors"></spring:message></caption>
	<thead>
		<tr>
			<th><spring:message code="Name"></spring:message></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${subcontractorAttribute}" var="subcontractor">
			<spring:url var="editUrl" value="/subcontractor/edit/${subcontractor.id}" />
			<tr>
				<td><c:out value="${subcontractor.name}" /></td>
				<td>
					<a class="btn btn-mini" href="${editUrl}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:if test="${empty subcontractorAttribute}">
<spring:message code="No records found"></spring:message>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>