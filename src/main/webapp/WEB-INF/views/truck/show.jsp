<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trucks" />
    <jsp:param name="property" value="truck" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="truck">
    <aripd:descriptionitem label="Region" field="truckAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Plate" field="truckAttribute.plate"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />