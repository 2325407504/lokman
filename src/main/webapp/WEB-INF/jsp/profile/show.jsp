<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="Profile"></spring:message></li>
		</ul>
		<dl class="dl-horizontal">
			<dt><spring:message code="Username"></spring:message></dt>
			<dd><c:out value="${profileAttribute.username}" /></dd>
			<dt><spring:message code="FirstName"></spring:message></dt>
			<dd><c:out value="${profileAttribute.firstName}" /></dd>
			<dt><spring:message code="LastName"></spring:message></dt>
			<dd><c:out value="${profileAttribute.lastName}" /></dd>
			<dt><spring:message code="E-mail Address"></spring:message></dt>
			<dd><c:out value="${profileAttribute.email}" /></dd>
		</dl>
		
		<c:url var="editUrl" value="/profile/edit"/>
		<div class="form-actions">
			<a class="btn" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
		</div>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>