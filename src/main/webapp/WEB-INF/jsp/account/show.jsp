<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/account/list" var="account_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${account_list}"><spring:message code="Accounts"></spring:message></a> <span class="divider">/</span></li>
	<li class="active"><spring:message code="Entry No"></spring:message>: ${accountAttribute.id}</li>
	<li class="pull-right">
		<spring:url var="editUrl" value="/account/edit/${accountAttribute.id}" />
		<a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
	</li>
</ul>

<ul class="unstyled">
	<li>
		<label class="label">
			<spring:message code="Id" text="Id"></spring:message>
		</label>
		${accountAttribute.id}
	</li>
	<li>
		<label class="label">
			<spring:message code="Date" text="Date"></spring:message>
		</label>
		<spring:eval expression="accountAttribute.dateOfBirth" />
	</li>
	<li>
		<label class="label">
			<spring:message code="Username"></spring:message>
		</label>
		${accountAttribute.username}
	</li>
	<li>
		<label class="label">
			<spring:message code="Fullname"></spring:message>
		</label>
		${accountAttribute.name}
	</li>
	<li>
		<label class="label">
			<spring:message code="E-mail Address"></spring:message>
		</label>
		${accountAttribute.email}
	</li>
</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>