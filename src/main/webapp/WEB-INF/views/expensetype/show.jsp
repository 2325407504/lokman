<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="expensetypeList" value="/expensetype/list" />
<spring:url var="expensetypeShow" value="/expensetype/show/${expensetypeAttribute.id}" />
<spring:url var="expensetypeNew" value="/expensetype/new" />
<spring:url var="expensetypeEdit" value="/expensetype/edit/${expensetypeAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${expensetypeList}"><spring:message code="Expensetypes" /></a></li>
    <li class="active"><a href="${expensetypeShow}"><spring:message code="Entry No" />: ${expensetypeAttribute.id}</a></li>
    <li class=""><a href="${expensetypeNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:description id="expensetype">
    <aripd:descriptionitem label="Name" field="expensetypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${expensetypeEdit}"><spring:message code="Edit" /></a>
    </div>

<jsp:include page="/WEB-INF/views/footer.jsp" />