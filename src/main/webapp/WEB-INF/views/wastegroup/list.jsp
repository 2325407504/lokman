<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Wastegroups" />
    <jsp:param name="property" value="wastegroup" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/wastegroup/get" id="wastegroups" dataUrlShow="/wastegroup/show" dataUrlEdit="/wastegroup/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />