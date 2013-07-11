<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Ending Points" />
    <jsp:param name="property" value="endingpoint" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="endingpoint">
    <aripd:descriptionitem label="Code" field="endingpointAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="endingpointAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />