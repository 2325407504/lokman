<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Profile"></spring:message></li>
		</ul>
		<dl class="dl-horizontal">
			<dt><spring:message code="Username"></spring:message></dt>
			<dd><c:out value="${userAttribute.username}" /></dd>
			<dt><spring:message code="Firstname"></spring:message></dt>
			<dd><c:out value="${userAttribute.firstName}" /></dd>
			<dt><spring:message code="Lastname"></spring:message></dt>
			<dd><c:out value="${userAttribute.lastName}" /></dd>
		</dl>
		
		<c:url var="editUrl" value="/profile/edit"/>
		<div class="form-actions">
			<a class="btn" href="${editUrl}"><spring:message code="button.edit"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>