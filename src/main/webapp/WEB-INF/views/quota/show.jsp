<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="quotaList" value="/quota/list" />
<spring:url var="quotaShow" value="/quota/show/${quotaAttribute.id}" />
<spring:url var="quotaNew" value="/quota/new" />
<spring:url var="quotaEdit" value="/quota/edit/${quotaAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${quotaList}"><spring:message code="Quotas"></spring:message></a></li>
	<li class="active"><a href="${quotaShow}"><spring:message code="Entry No"></spring:message>: ${quotaAttribute.id}</a></li>
	<li class=""><a href="${quotaNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:description id="quotas">
	<aripd:descriptionitem label="Code" field="${quotaAttribute.code}"></aripd:descriptionitem>
	<aripd:descriptionitem label="Name" field="${quotaAttribute.name}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
	<a class="btn" href="${quotaEdit}"><spring:message code="Edit"></spring:message></a>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>