<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Profile"></spring:message></li>
	<li class="pull-right">
		<spring:url var="editUrl" value="/profile/edit" />
		<a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
	</li>
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

<%@ include file="/WEB-INF/jsp/footer.jsp" %>