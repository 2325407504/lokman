<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Profile" /></li>
</ul>

<aripd:description id="profile">
    <aripd:descriptionitem label="Username" field="profileAttribute.username"></aripd:descriptionitem>
    <aripd:descriptionitem label="E-mail Address" field="profileAttribute.email"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <spring:url var="profile_edit" value="/profile/edit" />
    <a class="btn btn-primary" href="${profile_edit}"><spring:message code="Edit" /></a>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />