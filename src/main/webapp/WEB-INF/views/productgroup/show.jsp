<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productgroups" />
    <jsp:param name="property" value="productgroup" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="productgroup">
    <aripd:descriptionitem label="Name" field="productgroupAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />