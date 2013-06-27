<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/account/list" var="account_list" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li><a href="${account_list}"><spring:message code="Accounts" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Entry No" />: ${accountAttribute.id}</li>
    <li class="pull-right">
        <spring:url var="editUrl" value="/account/edit/${accountAttribute.id}" />
        <a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit" /></a>
    </li>
</ul>

<c:forEach var="role" items="${accountAttribute.roles}"><span class="label label-success">${role.name}</span>&nbsp;</c:forEach>
<aripd:description id="account">
    <aripd:descriptionitem label="Username" field="accountAttribute.username"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="accountAttribute.client.fullname"></aripd:descriptionitem>
    <aripd:descriptionitem label="E-mail Address" field="accountAttribute.email"></aripd:descriptionitem>
    <aripd:descriptionitem label="Region" field="accountAttribute.region.name"></aripd:descriptionitem>
</aripd:description>

<%@ include file="/WEB-INF/views/footer.jsp" %>