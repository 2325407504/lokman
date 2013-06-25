<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="weighbridgeList" value="/weighbridge/list" />
<spring:url var="weighbridgeShow" value="/weighbridge/show/${weighbridgeAttribute.id}" />
<spring:url var="weighbridgeEdit" value="/weighbridge/edit/${weighbridgeAttribute.id}" />
<spring:url var="weighbridgeSubmit" value="/weighbridge/submit/${weighbridgeAttribute.id}" />
<spring:url var="weighbridgeNew" value="/weighbridge/new" />
<spring:url var="weighbridgeExport" value="/weighbridge/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${weighbridgeList}"><spring:message code="Weighbridges"></spring:message></a></li>
    <li class="active"><a href="${weighbridgeShow}"><spring:message code="Entry No"></spring:message>: ${weighbridgeAttribute.id}</a></li>
    <li class=""><a href="${weighbridgeNew}"><spring:message code="New Entry"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${weighbridgeExport}"><spring:message code="Weighbridges"></spring:message></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${weighbridgeAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user"></spring:message>
        <a href="${weighbridgeEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${weighbridgeSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
        </div>
</c:if>
<c:if test="${!weighbridgeAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${weighbridgeEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${weighbridgeSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <ul class="unstyled">
            <li>
                <label class="label">
                    <spring:message code="Account"></spring:message>
                    </label>
                ${weighbridgeAttribute.account.client.fullname}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Weighbridgetype"></spring:message>
                    </label>
                ${weighbridgeAttribute.weighbridgetype.name}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Date"></spring:message>
                    </label>
                <spring:eval expression="weighbridgeAttribute.documentDate" />
            </li>
            <li>
                <label class="label">
                    <spring:message code="Company"></spring:message>
                    </label>
                ${weighbridgeAttribute.company}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Description"></spring:message>
                    </label>
                ${weighbridgeAttribute.description}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Amount"></spring:message>
                    </label>
                ${weighbridgeAttribute.amount}
            </li>
        </ul>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>