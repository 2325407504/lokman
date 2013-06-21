<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productList" value="/product/list" />
<spring:url var="productShow" value="/product/show/${productAttribute.id}" />
<spring:url var="productNew" value="/product/new" />
<spring:url var="productEdit" value="/product/edit/${productAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productList}"><spring:message code="Products"></spring:message></a></li>
    <li class="active"><a href="${productShow}"><spring:message code="Entry No"></spring:message>: ${productAttribute.id}</a></li>
    <li class=""><a href="${productNew}"><spring:message code="New Entry"></spring:message></a></li>
    </ul>

<aripd:description id="products">
    <aripd:descriptionitem label="Product Group" field="${productAttribute.productgroup.name}"></aripd:descriptionitem>
    <aripd:descriptionitem label="Code" field="${productAttribute.code}"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="${productAttribute.name}"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${productEdit}"><spring:message code="Edit"></spring:message></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>