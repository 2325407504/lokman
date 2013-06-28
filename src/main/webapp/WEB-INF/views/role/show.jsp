<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Roles" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Roles" />
    <jsp:param name="property" value="role" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="role">
    <aripd:descriptionitem label="Id" field="roleAttribute.id"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="roleAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="roleAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />