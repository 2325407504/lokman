<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Payments" />
    <jsp:param name="property" value="payment" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/payment/get" id="payments" dataUrlShow="/payment/show" dataUrlEdit="/payment/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Employee" field="employee.fullname"/>
    <aripd:datatablescolumn label="Paymenttype" field="paymenttype.name"/>
    <aripd:datatablescolumn label="Document Date" field="documentDate"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
    <aripd:datatablescolumn label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />