<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/expense/list" />
<spring:url var="expenseShow" value="/expense/show/${expenseAttribute.id}" />
<spring:url var="expenseEdit" value="/expense/edit/${expenseAttribute.id}" />
<spring:url var="expenseSubmit" value="/expense/submit/${expenseAttribute.id}" />
<spring:url var="expenseNew" value="/expense/new" />
<spring:url var="expenseExport" value="/expense/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${expenseList}"><spring:message code="Expenses" /></a></li>
    <li class="active"><a href="${expenseShow}"><spring:message code="Entry No" />: ${expenseAttribute.id}</a></li>
    <li class=""><a href="${expenseNew}"><spring:message code="New Entry" /></a></li>
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

<c:if test="${expenseAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${expenseEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${expenseSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
        </div>
</c:if>
<c:if test="${!expenseAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${expenseEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${expenseSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="expense">
            <aripd:descriptionitem label="Account" field="expenseAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Expensetype" field="expenseAttribute.expensetype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="expenseAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="expenseAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Description" field="expenseAttribute.description"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="expenseAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />