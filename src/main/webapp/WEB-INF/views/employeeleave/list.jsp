<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/employeeleave/get" id="employeeleaves" dataUrlShow="/employeeleave/show" dataUrlEdit="/employeeleave/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Fullname" field="account.employee.fullname"/>
    <aripd:datatablescolumn label="Account" field="account.username"/>
    <aripd:datatablescolumn label="Employee Leave Type" field="employeeleavetype.name"/>
    <aripd:datatablescolumn label="Date" field="documentDate"/>
    <aripd:datatablescolumn label="Company" field="company"/>
    <aripd:datatablescolumn label="Description" field="description"/>
    <aripd:datatablescolumn label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />