<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="electricmeterList" value="/electricmeter/list" />
<spring:url var="electricmeterShow" value="/electricmeter/show/${electricmeterAttribute.id}" />
<spring:url var="electricmeterNew" value="/electricmeter/new" />
<spring:url var="electricmeterEdit" value="/electricmeter/edit/${electricmeterAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${electricmeterList}"><spring:message code="Electricmeters"></spring:message></a></li>
    <li class="active"><a href="${electricmeterShow}"><spring:message code="Entry No"></spring:message>: ${electricmeterAttribute.id}</a></li>
    <li class=""><a href="${electricmeterNew}"><spring:message code="New Entry"></spring:message></a></li>
    </ul>

<aripd:description id="electricmeter">
    <aripd:descriptionitem label="Name" field="electricmeterAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${electricmeterEdit}"><spring:message code="Edit"></spring:message></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>