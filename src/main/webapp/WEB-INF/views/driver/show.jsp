<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Drivers" />
    <jsp:param name="property" value="driver" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="driver">
    <aripd:descriptionitem label="Region" field="driverAttribute.region.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="driverAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="driverAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Phone Number" field="driverAttribute.phonenumber"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />