<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="shiftList" value="/shift/list" />
<spring:url var="shiftShow" value="/shift/show/${shiftAttribute.id}" />
<spring:url var="shiftNew" value="/shift/new" />
<spring:url var="shiftEdit" value="/shift/edit/${shiftAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${shiftList}"><spring:message code="Shifts" /></a></li>
    <li class="active"><a href="${shiftShow}"><spring:message code="Entry No" />: ${shiftAttribute.id}</a></li>
    <li class=""><a href="${shiftNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="shift">
    <aripd:descriptionitem label="Code" field="shiftAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="shiftAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${shiftEdit}"><spring:message code="Edit" /></a>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>