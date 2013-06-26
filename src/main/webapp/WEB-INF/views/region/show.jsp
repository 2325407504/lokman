<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="regionList" value="/region/list" />
<spring:url var="regionShow" value="/region/show/${regionAttribute.id}" />
<spring:url var="regionNew" value="/region/new" />
<spring:url var="regionEdit" value="/region/edit/${regionAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${regionList}"><spring:message code="Regions"></spring:message></a></li>
    <li class="active"><a href="${regionShow}"><spring:message code="Entry No"></spring:message>: ${regionAttribute.id}</a></li>
    <li class=""><a href="${regionNew}"><spring:message code="New Entry"></spring:message></a></li>
    </ul>

<aripd:description id="region">
    <aripd:descriptionitem label="Name" field="regionAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${regionEdit}"><spring:message code="Edit"></spring:message></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>