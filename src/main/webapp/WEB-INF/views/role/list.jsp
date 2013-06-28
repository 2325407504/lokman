<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Roles" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="addUrl" value="/role/new"/>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Roles" /></li>
    <li class="pull-right">
        <a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry" /></a>
    </li>
</ul>

<aripd:datatables datasource="/role/get" id="roles" dataUrlShow="/role/show" dataUrlEdit="/role/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />