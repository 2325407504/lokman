<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Subcontractors" />
    <jsp:param name="property" value="subcontractor" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="subcontractor">
    <aripd:descriptionitem label="Region" field="subcontractorAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="subcontractorAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="subcontractorAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />