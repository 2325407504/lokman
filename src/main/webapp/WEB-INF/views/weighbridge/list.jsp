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
    <aripd:column label="Account" field="account.client.fullname"/>
    <aripd:column label="Clerk" field="clerk"/>
    <aripd:column label="Plate" field="plate"/>
    <aripd:column label="Driver" field="driver"/>
    <aripd:column label="Location From" field="locationFrom"/>
    <aripd:column label="Location To" field="locationTo"/>
    <aripd:column label="Check-in" field="checkin"/>
    <aripd:column label="Check-out" field="checkout"/>
    <aripd:column label="Good Type" field="goodtype"/>
    <aripd:column label="Customer" field="customer"/>
    <aripd:column label="First Weighing" field="firstWeighing"/>
    <aripd:column label="Last Weighing" field="lastWeighing"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>