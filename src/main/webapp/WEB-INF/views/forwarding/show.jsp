<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingShow" value="/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingSubmit" value="/forwarding/submit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingImport" value="/forwarding/import/xls" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
    <li class="active"><a href="${forwardingShow}"><spring:message code="Entry No" />: ${forwardingAttribute.id}</a></li>
    <li class=""><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${forwardingImport}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${forwardingExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${uatfExport}"><spring:message code="UATF" /></a></li>
            </ul>
        </li>
    </ul>

<c:if test="${forwardingAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${forwardingEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${forwardingSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
        </div>
</c:if>
<c:if test="${!forwardingAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${forwardingEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${forwardingSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
        </div>
</c:if>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="forwarding">
            <aripd:descriptionitem label="Account" field="forwardingAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Waybill No" field="forwardingAttribute.waybillNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="forwardingAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="forwardingAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="forwardingAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="forwardingAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="forwardingAttribute.endingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="forwardingAttribute.loadWeightInTonne"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shipping Cost" field="forwardingAttribute.shippingCost"></aripd:descriptionitem>
            <aripd:descriptionitem label="Subcontractor" field="forwardingAttribute.subcontractor.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Quota" field="forwardingAttribute.quota.name"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span8">
        <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs">
            <aripd:column label="Code" field="code"/>
            <aripd:column label="Company" field="company"/>
            <aripd:column label="County" field="county"/>
            <aripd:column label="City" field="city"/>
            <aripd:column label="Weight" field="loadWeightInTonne"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />