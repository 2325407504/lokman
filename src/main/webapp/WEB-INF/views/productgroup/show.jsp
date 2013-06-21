<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productgroupList" value="/productgroup/list" />
<spring:url var="productgroupShow" value="/productgroup/show/${productgroupAttribute.id}" />
<spring:url var="productgroupNew" value="/productgroup/new" />
<spring:url var="productgroupEdit" value="/productgroup/edit/${productgroupAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${productgroupList}"><spring:message code="Product Groups"></spring:message></a></li>
	<li class="active"><a href="${productgroupShow}"><spring:message code="Entry No"></spring:message>: ${productgroupAttribute.id}</a></li>
	<li class=""><a href="${productgroupNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:description id="productgroups">
	<aripd:descriptionitem label="Name" field="${productgroupAttribute.name}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
	<a class="btn" href="${productgroupEdit}"><spring:message code="Edit"></spring:message></a>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>