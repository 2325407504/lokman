<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Customers" />
    <jsp:param name="property" value="customer" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="customer">
    <aripd:descriptionitem label="Tax No" field="customerAttribute.taxNo"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="customerAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Phone Number" field="customerAttribute.phonenumber"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />