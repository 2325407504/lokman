<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

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
    <li class=""><a href="${productionList}"><spring:message code="Productions" /></a></li>
    <li class="active"><a href="${productionShow}"><spring:message code="Entry No" />: ${productionAttribute.id}</a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${productionImport}"><spring:message code="Import" /></a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${productionExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${bigbagExport}"><spring:message code="UATF" /></a></li>
        </ul>
    </li>
</ul>

<c:if test="${productionAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${productionEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${productionSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
    </div>
</c:if>
<c:if test="${!productionAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${productionEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${productionSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
    </div>
</c:if>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="production">
            <aripd:descriptionitem label="Account" field="productionAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Date" field="productionAttribute.shiftdate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shift" field="productionAttribute.shift.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="productionAttribute.feed"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="productionAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
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
            <caption><spring:message code="Compensations" /></caption>
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

<jsp:include page="/WEB-INF/views/footer.jsp" />