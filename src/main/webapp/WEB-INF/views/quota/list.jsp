<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="quotaList" value="/quota/list" />
<spring:url var="quotaNew" value="/quota/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${quotaList}"><spring:message code="Quotas" /></a></li>
	<li class=""><a href="${quotaNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/quota/get" id="quotas" dataUrlShow="/quota/show" dataUrlEdit="/quota/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Code" field="code"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />