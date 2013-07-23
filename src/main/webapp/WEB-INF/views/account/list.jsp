<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Accounts" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Accounts" />
    <jsp:param name="property" value="account" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/account/get" id="accounts" dataUrlShow="/account/show" dataUrlEdit="/account/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Region" field="region.name"/>
    <aripd:datatablescolumn label="Username" field="username"/>
    <aripd:datatablescolumn label="Fullname" field="employee.fullname"/>
    <aripd:datatablescolumn label="E-mail Address" field="email"/>
    <aripd:datatablescolumn label="Phone Number" field="employee.phonenumber"/>
    <aripd:datatablescolumn label="Roles" field="rolesAsString"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />