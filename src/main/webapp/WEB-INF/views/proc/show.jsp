<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Procedures" />
    <jsp:param name="property" value="proc" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="proc">
    <aripd:descriptionitem label="Status" field="procAttribute.active"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="procAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Description" field="procAttribute.description"></aripd:descriptionitem>
    <aripd:descriptionitem label="Content" field="procAttribute.content"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />