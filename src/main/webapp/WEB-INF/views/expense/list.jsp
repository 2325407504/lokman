<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expenses" />
    <jsp:param name="property" value="expense" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/expense/get" id="expenses" dataUrlShow="/expense/show" dataUrlEdit="/expense/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Fullname" field="account.client.fullname"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Expensetype" field="expensetype.name"/>
    <aripd:column label="Date" field="documentDate"/>
    <aripd:column label="Company" field="company"/>
    <aripd:column label="Description" field="description"/>
    <aripd:column label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />