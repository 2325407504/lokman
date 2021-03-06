<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/employeeleave/get" id="employeeleaves" dataUrlShow="/employeeleave/show" dataUrlEdit="/employeeleave/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Employee" field="employee.fullname"/>
    <aripd:datatablescolumn label="Employee Leave Type" field="employeeleavetype.name"/>
    <aripd:datatablescolumn label="Starting Date" field="startingDate"/>
    <aripd:datatablescolumn label="Ending Date" field="endingDate"/>
    <aripd:datatablescolumn label="Day" field="nofWorkdays"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />