<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="quotaList" value="/quota/list" />
<spring:url var="quotaShow" value="/quota/show/${quotaAttribute.id}" />
<spring:url var="quotaNew" value="/quota/new" />
<spring:url var="quotaEdit" value="/quota/edit/${quotaAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${quotaList}"><spring:message code="Quotas" /></a></li>
    <li class="active"><a href="${quotaShow}"><spring:message code="Entry No" />: ${quotaAttribute.id}</a></li>
    <li class=""><a href="${quotaNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="quota">
    <aripd:descriptionitem label="Code" field="quotaAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="quotaAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${quotaEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />