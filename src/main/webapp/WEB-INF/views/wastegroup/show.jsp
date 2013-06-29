<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Wastegroups" />
    <jsp:param name="property" value="wastegroup" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="wastegroup">
    <aripd:descriptionitem label="Name" field="wastegroupAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />