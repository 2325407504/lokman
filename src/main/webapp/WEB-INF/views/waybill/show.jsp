<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="waybillList" value="/waybill/list" />
<spring:url var="waybillShow" value="/waybill/show/${waybillAttribute.id}" />
<spring:url var="waybillEdit" value="/waybill/edit/${waybillAttribute.id}" />
<spring:url var="waybillSubmit" value="/waybill/submit/${waybillAttribute.id}" />
<spring:url var="waybillNew" value="/waybill/new" />
<spring:url var="waybillImport" value="/waybill/import/xls" />
<spring:url var="waybillExport" value="/waybill/export/xls" />
<spring:url var="outgoingExport" value="/outgoing/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${waybillList}"><spring:message code="Waybills" /></a></li>
    <li class="active"><a href="${waybillShow}"><spring:message code="Entry No" />: ${waybillAttribute.id}</a></li>
    <li class=""><a href="${waybillNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${waybillImport}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${waybillExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${outgoingExport}"><spring:message code="Outgoing" /></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${waybillAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${waybillEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${waybillSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
        </div>
</c:if>
<c:if test="${!waybillAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${waybillEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${waybillSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="waybill-other">
            <aripd:descriptionitem label="Status" field="waybillAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="waybillAttribute.account.client.fullname"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <aripd:description id="waybill">
            <aripd:descriptionitem label="Document No" field="waybillAttribute.documentNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="waybillAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="waybillAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="waybillAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="waybillAttribute.plate"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <aripd:description id="waybill-invoice">
            <aripd:descriptionitem label="Name" field="waybillAttribute.invoice.customer.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Postal Address" field="waybillAttribute.invoice.customer.address"></aripd:descriptionitem>
            <aripd:descriptionitem label="Phone Number" field="waybillAttribute.invoice.customer.phonenumber"></aripd:descriptionitem>
            <aripd:descriptionitem label="Tax No" field="waybillAttribute.invoice.customer.taxNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Tax Office" field="waybillAttribute.invoice.customer.taxOffice"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document No" field="waybillAttribute.invoice.documentNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="waybillAttribute.invoice.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="waybillAttribute.invoice.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings">
            <aripd:column label="Code" field="code"/>
            <aripd:column label="Company" field="company"/>
            <aripd:column label="County" field="county"/>
            <aripd:column label="City" field="city"/>
            <aripd:column label="Weight" field="loadWeightInTonne"/>
        </aripd:datatables>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>