<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expenses" />
    <jsp:param name="property" value="userexpense" />
    <jsp:param name="new" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/userexpense/get" id="expenses" dataUrlShow="/userexpense/show" dataUrlEdit="/userexpense/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Expensetype" field="expensetype.name"/>
    <aripd:datatablescolumn label="Date" field="documentDate"/>
    <aripd:datatablescolumn label="Company" field="company"/>
    <aripd:datatablescolumn label="Description" field="description"/>
    <aripd:datatablescolumn label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />