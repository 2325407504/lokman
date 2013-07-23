<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Starting Points" />
    <jsp:param name="property" value="startingpoint" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="startingpoint">
    <aripd:descriptionitem label="Code" field="startingpointAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="startingpointAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />