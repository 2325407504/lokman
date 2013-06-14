<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expensetypeList" value="/expensetype/list" />
<spring:url var="expensetypeShow" value="/expensetype/show/${expensetypeAttribute.id}" />
<spring:url var="expensetypeNew" value="/expensetype/new" />
<spring:url var="expensetypeEdit" value="/expensetype/edit/${expensetypeAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expensetypeList}"><spring:message code="Expensetypes"></spring:message></a></li>
	<li class="active"><a href="${expensetypeShow}"><spring:message code="Entry No"></spring:message>: ${expensetypeAttribute.id}</a></li>
	<li class=""><a href="${expensetypeNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:description id="expensetypes">
	<aripd:descriptionitem label="Name" field="${expensetypeAttribute.name}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
	<a class="btn" href="${expensetypeEdit}"><spring:message code="Edit"></spring:message></a>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>