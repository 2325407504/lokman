<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="label.trucks"></spring:message></li>
		</ul>
		<table class="table">
			<caption><spring:message code="label.trucks"></spring:message></caption>
			<thead>
				<tr>
					<th>Plate</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${entities}" var="truck">
					<c:url var="editUrl" value="/truck/edit/${truck.id}" />
					<c:url var="deleteUrl" value="/truck/delete?id=${truck.id}" />
					<tr>
						<td><c:out value="${truck.plate}" /></td>
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
		
		<c:url var="addUrl" value="/truck/new" />
		<div class="form-actions">
			<a class="btn" href="${addUrl}"><spring:message code="button.new"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>