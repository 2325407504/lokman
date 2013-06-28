<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="wasteList" value="/waste/list" />
<spring:url var="wasteNew" value="/waste/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${wasteList}"><spring:message code="Wastes" /></a></li>
    <li class=""><a href="${wasteNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/waste/get" id="wastes" dataUrlShow="/waste/show" dataUrlEdit="/waste/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Waste Group" field="wastegroup.name"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />