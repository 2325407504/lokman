<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Members" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Members" />
    <jsp:param name="property" value="member" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/member/get" id="members" dataUrlShow="/member/show" dataUrlEdit="/member/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Active" field="active"/>
    <aripd:datatablescolumn label="Username" field="username"/>
    <aripd:datatablescolumn label="E-mail Address" field="email"/>
    <aripd:datatablescolumn label="Roles" field="rolesAsString"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />