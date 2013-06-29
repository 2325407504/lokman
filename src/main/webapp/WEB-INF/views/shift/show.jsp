<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Shifts" />
    <jsp:param name="property" value="shift" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="shift">
    <aripd:descriptionitem label="Code" field="shiftAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="shiftAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />