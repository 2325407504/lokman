<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/role/list" var="role_list" />
<spring:url var="editUrl" value="/role/edit/${roleAttribute.id}" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${role_list}"><spring:message code="Roles"></spring:message></a> <span class="divider">/</span></li>
	<li class="active"><spring:message code="Entry No"></spring:message>: ${roleAttribute.id}</li>
	<li class="pull-right">
		<a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
	</li>
</ul>

<ul class="unstyled">
	<li>
		<label class="label">
			<spring:message code="Id" text="Id"></spring:message>
		</label>
		${roleAttribute.id}
	</li>
	<li>
		<label class="label">
			<spring:message code="Code"></spring:message>
		</label>
		${roleAttribute.code}
	</li>
	<li>
		<label class="label">
			<spring:message code="Name"></spring:message>
		</label>
		${roleAttribute.name}
	</li>
</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>