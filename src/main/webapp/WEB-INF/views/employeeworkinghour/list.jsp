<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hours" />
    <jsp:param name="property" value="employeeworkinghour" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/employeeworkinghour/get" id="employeeworkinghours" dataUrlShow="/employeeworkinghour/show" dataUrlEdit="/employeeworkinghour/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Fullname" field="account.employee.fullname"/>
    <aripd:datatablescolumn label="Employee Working Hour Type" field="employeeworkinghourtype.name"/>
    <aripd:datatablescolumn label="Starting Date" field="startingDate"/>
    <aripd:datatablescolumn label="Ending Date" field="endingDate"/>
    <aripd:datatablescolumn label="Ending Date" field="nofWorkdays"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />