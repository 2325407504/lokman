<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Profile"></spring:message></li>
        <li class="pull-right">
        <spring:url var="profile_edit" value="/profile/edit" />
        <a class="btn btn-mini" href="${profile_edit}"><spring:message code="Edit"></spring:message></a>
        </li>
    </ul>

<aripd:description id="profile">
    <aripd:descriptionitem label="Username" field="profileAttribute.username"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="profileAttribute.client.fullname"></aripd:descriptionitem>
    <aripd:descriptionitem label="E-mail Address" field="profileAttribute.email"></aripd:descriptionitem>
</aripd:description>

<%@ include file="/WEB-INF/views/footer.jsp" %>