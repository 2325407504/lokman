<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="machineList" value="/machine/list" />
<spring:url var="machineShow" value="/machine/show/${machineAttribute.id}" />
<spring:url var="machineNew" value="/machine/new" />
<spring:url var="machineEdit" value="/machine/edit/${machineAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${machineList}"><spring:message code="Machines" /></a></li>
    <li class="active"><a href="${machineShow}"><spring:message code="Entry No" />: ${machineAttribute.id}</a></li>
    <li class=""><a href="${machineNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="machine">
    <aripd:descriptionitem label="Name" field="machineAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${machineEdit}"><spring:message code="Edit" /></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>