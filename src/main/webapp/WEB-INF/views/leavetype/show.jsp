<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Leavetypes" />
    <jsp:param name="property" value="leavetype" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="leavetype">
    <aripd:descriptionitem label="Code" field="leavetypeAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="leavetypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />