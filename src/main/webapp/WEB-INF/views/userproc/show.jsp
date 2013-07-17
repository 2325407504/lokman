<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Procedures" /></li>
</ul>

<aripd:description id="userproc">
    <aripd:descriptionitem label="Name" field="userprocAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Description" field="userprocAttribute.description"></aripd:descriptionitem>
    <aripd:descriptionitem label="Content" field="userprocAttribute.content"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />