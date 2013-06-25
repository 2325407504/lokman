<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionShow" value="/production/show/${productionAttribute.id}" />
<spring:url var="productionEdit" value="/production/edit/${productionAttribute.id}" />
<spring:url var="productionSubmit" value="/production/submit/${productionAttribute.id}" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionImport" value="/production/import/xls" />
<spring:url var="productionExport" value="/production/export/xls" />
<spring:url var="bigbagExport" value="/bigbag/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productionList}"><spring:message code="Productions"></spring:message></a></li>
    <li class="active"><a href="${productionShow}"><spring:message code="Entry No"></spring:message>: ${productionAttribute.id}</a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry"></spring:message></a></li>
    <li class=""><a href="${productionImport}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${productionExport}"><spring:message code="Waybill"></spring:message></a></li>
            <li><a href="${bigbagExport}"><spring:message code="UATF"></spring:message></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${productionAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user"></spring:message>
        <a href="${productionEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${productionSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
        </div>
</c:if>
<c:if test="${!productionAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${productionEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
        <a href="${productionSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span4">
        <ul class="unstyled">
            <li>
                <label class="label">
                    <spring:message code="Account"></spring:message>
                    </label>
                ${productionAttribute.account.client.fullname}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Date"></spring:message>
                    </label>
                <spring:eval expression="productionAttribute.shiftdate" />
            </li>
            <li>
                <label class="label">
                    <spring:message code="Shift"></spring:message>
                    </label>
                ${productionAttribute.shift.name}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Weight"></spring:message>
                    </label>
                ${productionAttribute.feed}
            </li>
            <li>
                <label class="label">
                    <spring:message code="Remark"></spring:message>
                    </label>
                ${productionAttribute.remark}
            </li>
        </ul>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Machine Times" /></caption>
            <c:forEach items="${productionAttribute.machinetimes}" var="machinetime">
                <tr>
                    <td>${machinetime.machine.name}</td>
                    <td>${machinetime.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Compensation Table" /></caption>
            <c:forEach items="${productionAttribute.compensations}" var="compensation">
                <tr>
                    <td>${compensation.electricmeter.name}</td>
                    <td>${compensation.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/bigbag/get/${productionAttribute.id}" id="bigbags" caption="Production Amounts">
            <aripd:column label="Product" field="product.name"/>
            <aripd:column label="Weight" field="weight"/>
        </aripd:datatables>
    </div>
</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>