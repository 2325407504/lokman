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
    <li class=""><a href="${weighbridgeList}"><spring:message code="Weighbridges" /></a></li>
    <li class="active"><a href="${weighbridgeShow}"><spring:message code="Entry No" />: ${weighbridgeAttribute.id}</a></li>
    <li class=""><a href="${weighbridgeNew}"><spring:message code="New Entry" /></a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${weighbridgeExport}"><spring:message code="Weighbridges" /></a></li>
        </ul>
    </li>
</ul>

<c:if test="${weighbridgeAttribute.submitted}">
    <div class="alert alert-error">
        <spring:message code="Submitted by user" />
        <a href="${weighbridgeEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${weighbridgeSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back" /></a>
    </div>
</c:if>
<c:if test="${!weighbridgeAttribute.submitted}">
    <div class="alert alert-info">
        <a href="${weighbridgeEdit}"><i class="icon-pencil"></i> <spring:message code="Edit" /></a>
        <a href="${weighbridgeSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit" /></a>
    </div>
</c:if>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="weighbridge">
            <aripd:descriptionitem label="Status" field="weighbridgeAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="weighbridgeAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Clerk" field="weighbridgeAttribute.clerk"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="weighbridgeAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="weighbridgeAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Location From" field="weighbridgeAttribute.locationFrom"></aripd:descriptionitem>
            <aripd:descriptionitem label="Location To" field="weighbridgeAttribute.locationTo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Check-in" field="weighbridgeAttribute.checkin"></aripd:descriptionitem>
            <aripd:descriptionitem label="Check-out" field="weighbridgeAttribute.checkout"></aripd:descriptionitem>
            <aripd:descriptionitem label="Good Type" field="weighbridgeAttribute.goodtype"></aripd:descriptionitem>
            <aripd:descriptionitem label="Customer" field="weighbridgeAttribute.customer"></aripd:descriptionitem>
            <aripd:descriptionitem label="First Weighing" field="weighbridgeAttribute.firstWeighing"></aripd:descriptionitem>
            <aripd:descriptionitem label="Last Weighing" field="weighbridgeAttribute.lastWeighing"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Extrications" /></caption>
            <c:forEach items="${weighbridgeAttribute.extrications}" var="extrication">
                <tr>
                    <td>${extrication.waste.name}</td>
                    <td>${extrication.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>