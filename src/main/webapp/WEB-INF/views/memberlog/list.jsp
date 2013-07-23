<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Memberlogs" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Memberlogs" />
    <jsp:param name="property" value="memberlog" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/memberlog/get" id="memberlogs">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.employee.fullname"/>
    <aripd:datatablescolumn label="Name" field="sessionId"/>
    <aripd:datatablescolumn label="Name" field="ipAddress"/>
    <aripd:datatablescolumn label="Name" field="executeTime"/>
    <aripd:datatablescolumn label="Name" field="url"/>
    <aripd:datatablescolumn label="Name" field="type"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />