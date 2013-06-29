<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Products" />
    <jsp:param name="property" value="product" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="product">
    <aripd:descriptionitem label="Product Group" field="productAttribute.productgroup.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="productAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="productAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />