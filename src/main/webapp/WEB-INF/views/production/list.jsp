<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionImport" value="/production/import/xls" />
<spring:url var="productionReport" value="/production/report" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${productionList}"><spring:message code="Productions"></spring:message></a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry"></spring:message></a></li>
    <li class=""><a href="${productionImport}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Reports"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${productionReport}"><spring:message code="Report"></spring:message></a></li>
            </ul>
        </li>
    </ul>

<aripd:datatables datasource="/production/get" id="productions" dataUrlShow="/production/show" dataUrlEdit="/production/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Date" field="shiftdate"/>
    <aripd:column label="Shift" field="shift.name"/>
    <aripd:column label="Weight" field="feed"/>
    <aripd:column label="Remark" field="remark"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>