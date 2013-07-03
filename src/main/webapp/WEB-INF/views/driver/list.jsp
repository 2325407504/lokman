<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Drivers" />
    <jsp:param name="property" value="driver" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/driver/get" id="drivers" dataUrlShow="/driver/show" dataUrlEdit="/driver/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Region" field="region.name"/>
    <aripd:datatablescolumn label="Code" field="code"/>
    <aripd:datatablescolumn label="Fullname" field="name"/>
    <aripd:datatablescolumn label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />