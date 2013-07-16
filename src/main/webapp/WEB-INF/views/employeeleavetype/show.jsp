<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employeeleavetypes" />
    <jsp:param name="property" value="employeeleavetype" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="employeeleavetype">
    <aripd:descriptionitem label="Code" field="employeeleavetypeAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="employeeleavetypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />