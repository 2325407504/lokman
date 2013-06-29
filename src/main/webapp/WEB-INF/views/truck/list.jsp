<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trucks" />
    <jsp:param name="property" value="truck" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/truck/get" id="trucks" dataUrlShow="/truck/show" dataUrlEdit="/truck/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Region" field="region.name"/>
    <aripd:column label="Plate" field="plate"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />