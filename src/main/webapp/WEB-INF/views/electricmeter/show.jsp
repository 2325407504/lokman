<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Electricmeters" />
    <jsp:param name="property" value="electricmeter" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="electricmeter">
    <aripd:descriptionitem label="Name" field="electricmeterAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />