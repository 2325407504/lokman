<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="regionList" value="/region/list" />
<spring:url var="regionShow" value="/region/show/${regionAttribute.id}" />
<spring:url var="regionNew" value="/region/new" />
<spring:url var="regionEdit" value="/region/edit/${regionAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${regionList}"><spring:message code="Regions" /></a></li>
    <li class="active"><a href="${regionShow}"><spring:message code="Entry No" />: ${regionAttribute.id}</a></li>
    <li class=""><a href="${regionNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="region">
    <aripd:descriptionitem label="Name" field="regionAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${regionEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />