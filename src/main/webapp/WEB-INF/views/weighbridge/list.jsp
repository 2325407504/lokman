<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="weighbridgeList" value="/weighbridge/list" />
<spring:url var="weighbridgeNew" value="/weighbridge/new" />
<spring:url var="weighbridgeImport" value="/weighbridge/import/xls" />
<spring:url var="weighbridgeExport" value="/weighbridge/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${weighbridgeList}"><spring:message code="Weighbridges"></spring:message></a></li>
    <li class=""><a href="${weighbridgeNew}"><spring:message code="New Entry"></spring:message></a></li>
    <li class=""><a href="${weighbridgeImport}"><spring:message code="Import"></spring:message></a></li>
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

<aripd:datatables datasource="/weighbridge/get" id="weighbridges" dataUrlShow="/weighbridge/show" dataUrlEdit="/weighbridge/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Fullname" field="account.client.fullname"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Date" field="documentDate"/>
    <aripd:column label="Company" field="company"/>
    <aripd:column label="Description" field="description"/>
    <aripd:column label="Amount" field="amount"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>