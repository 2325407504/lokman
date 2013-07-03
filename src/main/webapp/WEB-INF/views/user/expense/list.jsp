<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/user/expense/list" />
<spring:url var="expenseNew" value="/user/expense/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${expenseList}"><spring:message code="Expenses" /></a></li>
    <li class=""><a href="${expenseNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/user/expense/get" id="expenses" dataUrlShow="/user/expense/show" dataUrlEdit="/user/expense/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Expensetype" field="expensetype.name"/>
    <aripd:datatablescolumn label="Date" field="documentDate"/>
    <aripd:datatablescolumn label="Company" field="company"/>
    <aripd:datatablescolumn label="Description" field="description"/>
    <aripd:datatablescolumn label="Amount" field="amount"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />