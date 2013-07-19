<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hour Types" />
    <jsp:param name="property" value="employeeworkinghourtype" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="employeeworkinghourtype">
    <aripd:descriptionitem label="Code" field="employeeworkinghourtypeAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="employeeworkinghourtypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />