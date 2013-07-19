<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hour Types" />
    <jsp:param name="property" value="employeeworkinghourtype" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/employeeworkinghourtype/get" id="employeeworkinghourtypes" dataUrlShow="/employeeworkinghourtype/show" dataUrlEdit="/employeeworkinghourtype/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Code" field="code"/>
    <aripd:datatablescolumn label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />