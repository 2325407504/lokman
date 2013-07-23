<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Wastes" />
    <jsp:param name="property" value="waste" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="waste">
    <aripd:descriptionitem label="Waste Group" field="wasteAttribute.wastegroup.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="wasteAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="wasteAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />