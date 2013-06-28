<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

${param.title}

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionShow" value="/production/show/${productionAttribute.id}" />
<spring:url var="productionEdit" value="/production/edit/${productionAttribute.id}" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionSave" value="/production/save" />
<spring:url var="productionImport" value="/production/import/xls" />
<spring:url var="productionReport" value="/production/report" />
<spring:url var="compensationReport" value="/compensation/report" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productionList}"><spring:message code="Productions" /></a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${productionImport}"><spring:message code="Import" /></a></li>
    <li class="active dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Reports" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${productionReport}"><spring:message code="Production" /></a></li>
            <li><a href="${compensationReport}"><spring:message code="Compensation" /></a></li>
        </ul>
    </li>
</ul>