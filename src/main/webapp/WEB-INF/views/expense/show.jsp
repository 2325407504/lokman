<%@ include file="/WEB-INF/views/header.jsp" %>

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
    <li class=""><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
    <li class="active"><a href="${expenseShow}"><spring:message code="Entry No"></spring:message>: ${expenseAttribute.id}</a></li>
    <li class=""><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${expenseExport}"><spring:message code="Expenses"></spring:message></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${expenseAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user"></spring:message>
        <a href="${expenseEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${expenseSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
        </div>
</c:if>
<c:if test="${!expenseAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${expenseEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${expenseSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <ul class="unstyled">
            <li>
                <label class="label">
                    <spring:message code="Account"></spring:message>
                    </label>
                ${expenseAttribute.account.customer.fullname}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Expensetype"></spring:message>
                    </label>
                ${expenseAttribute.expensetype.name}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Date"></spring:message>
                    </label>
                <spring:eval expression="expenseAttribute.documentDate" />
            </li>
            <li>
                <label class="label">
                    <spring:message code="Company"></spring:message>
                    </label>
                ${expenseAttribute.company}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Description"></spring:message>
                    </label>
                ${expenseAttribute.description}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Amount"></spring:message>
                    </label>
                ${expenseAttribute.amount}
            </li>
        </ul>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>