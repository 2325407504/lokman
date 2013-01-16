<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Profile"></spring:message></li>
		</ul>
		<table class="table">
			<tbody>
				<tr>
					<td><spring:message code="Username"></spring:message></td>
					<td><c:out value="${userAttribute.username}" /></td>
				</tr>
			</tbody>
		</table>
		
		<c:url var="editUrl" value="/profile/edit"/>
		<div class="form-actions">
			<a class="btn" href="${editUrl}"><spring:message code="button.edit"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>