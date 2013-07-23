<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employees" />
    <jsp:param name="property" value="employee" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/employee/get" id="employees" dataUrlShow="/employee/show" dataUrlEdit="/employee/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="TC Kimlik No" field="tckimlikno"/>
    <aripd:datatablescolumn label="First Name" field="firstName"/>
    <aripd:datatablescolumn label="Last Name" field="lastName"/>
    <aripd:datatablescolumn label="Postal Address" field="address"/>
    <aripd:datatablescolumn label="Phone Number" field="phonenumber"/>
    <aripd:datatablescolumn label="Date of birth" field="birthdate"/>
    <aripd:datatablescolumn label="Date of starting the job" field="employmentDate"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />