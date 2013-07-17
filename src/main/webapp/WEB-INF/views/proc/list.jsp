<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Procedures" />
    <jsp:param name="property" value="proc" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/proc/get" id="procs" dataUrlShow="/proc/show" dataUrlEdit="/proc/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Status" field="active"/>
    <aripd:datatablescolumn label="Name" field="name"/>
    <aripd:datatablescolumn label="Description" field="description"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />