<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="wastegroupList" value="/wastegroup/list" />
<spring:url var="wastegroupShow" value="/wastegroup/show/${wastegroupAttribute.id}" />
<spring:url var="wastegroupNew" value="/wastegroup/new" />
<spring:url var="wastegroupEdit" value="/wastegroup/edit/${wastegroupAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${wastegroupList}"><spring:message code="Waste Groups" /></a></li>
    <li class="active"><a href="${wastegroupShow}"><spring:message code="Entry No" />: ${wastegroupAttribute.id}</a></li>
    <li class=""><a href="${wastegroupNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="wastegroup">
    <aripd:descriptionitem label="Name" field="wastegroupAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${wastegroupEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />