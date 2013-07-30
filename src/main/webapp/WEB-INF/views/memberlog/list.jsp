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
    <aripd:datatablescolumn label="Id" field="id"/>
    <aripd:datatablescolumn label="Member" field="member.username"/>
    <aripd:datatablescolumn label="Session Id" field="sessionId"/>
    <aripd:datatablescolumn label="IP Address" field="ipAddress"/>
    <aripd:datatablescolumn label="Created At" field="createdAt"/>
    <aripd:datatablescolumn label="Execute Time" field="executeTime"/>
    <aripd:datatablescolumn label="URL" field="url"/>
    <aripd:datatablescolumn label="Type" field="type"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />