<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Wastes" />
    <jsp:param name="property" value="waste" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/waste/get" id="wastes" dataUrlShow="/waste/show" dataUrlEdit="/waste/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Waste Group" field="wastegroup.name"/>
    <aripd:datatablescolumn label="Code" field="code"/>
    <aripd:datatablescolumn label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />