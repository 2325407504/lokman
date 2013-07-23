<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Invoices" />
    <jsp:param name="property" value="invoice" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/invoice/get" id="invoices" dataUrlShow="/invoice/show" dataUrlEdit="/invoice/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.username"/>
    <aripd:datatablescolumn label="Document No" field="documentNo"/>
    <aripd:datatablescolumn label="Document Date" field="documentDate"/>
    <aripd:datatablescolumn label="Customer" field="customer.name"/>
    <aripd:datatablescolumn label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />