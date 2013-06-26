<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="customerList" value="/customer/list" />
<spring:url var="customerShow" value="/customer/show/${customerAttribute.id}" />
<spring:url var="customerNew" value="/customer/new" />
<spring:url var="customerEdit" value="/customer/edit/${customerAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${customerList}"><spring:message code="Customers"></spring:message></a></li>
    <li class="active"><a href="${customerShow}"><spring:message code="Entry No"></spring:message>: ${customerAttribute.id}</a></li>
    <li class=""><a href="${customerNew}"><spring:message code="New Entry"></spring:message></a></li>
    </ul>

<aripd:description id="customer">
    <aripd:descriptionitem label="Tax No" field="customerAttribute.taxNo"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="customerAttribute.name"></aripd:descriptionitem>
    <aripd:descriptionitem label="Phone Number" field="customerAttribute.phonenumber"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${customerEdit}"><spring:message code="Edit"></spring:message></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>