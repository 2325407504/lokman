<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/role/list" var="role_list" />
<spring:url var="editUrl" value="/role/edit/${roleAttribute.id}" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li><a href="${role_list}"><spring:message code="Roles" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Entry No" />: ${roleAttribute.id}</li>
        <li class="pull-right">
            <a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit" /></a>
        </li>
    </ul>

<aripd:description id="role">
    <aripd:descriptionitem label="Id" field="roleAttribute.id"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="roleAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="roleAttribute.name"></aripd:descriptionitem>
</aripd:description>

<%@ include file="/WEB-INF/views/footer.jsp" %>