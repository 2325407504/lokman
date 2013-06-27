<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/user/forwarding/list" />
<spring:url var="forwardingShow" value="/user/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/user/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/user/forwarding/new" />
<spring:url var="forwardingSubmit" value="/user/forwarding/submit/${forwardingAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
    <li class="active"><a href="${forwardingShow}"><spring:message code="Entry No" />: ${forwardingAttribute.id}</a></li>
    <li class=""><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<c:if test="${forwardingAttribute.submitted}">
    <div class="alert alert-error"><spring:message code="You cannot edit this record anymore" /></div>
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
        <aripd:datatables datasource="/user/uatf/get/${forwardingAttribute.id}" id="uatfs">
            <aripd:column label="Code" field="code"/>
            <aripd:column label="Company" field="company"/>
            <aripd:column label="County" field="county"/>
            <aripd:column label="City" field="city"/>
            <aripd:column label="Weight" field="loadWeightInTonne"/>
        </aripd:datatables>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>