<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/expense/list" />
<spring:url var="expenseNew" value="/expense/new" />
<spring:url var="expenseImport" value="/expense/import/xls" />
<spring:url var="expenseExport" value="/expense/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${expenseList}"><spring:message code="Expenses" /></a></li>
    <li class=""><a href="${expenseNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${expenseImport}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${expenseExport}"><spring:message code="Expenses" /></a></li>
            </ul>
        </li>
    </ul>

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

<%@ include file="/WEB-INF/views/footer.jsp" %>