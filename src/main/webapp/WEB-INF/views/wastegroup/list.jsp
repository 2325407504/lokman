<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="wastegroupList" value="/wastegroup/list" />
<spring:url var="wastegroupNew" value="/wastegroup/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${wastegroupList}"><spring:message code="Waste Groups" /></a></li>
	<li class=""><a href="${wastegroupNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/wastegroup/get" id="wastegroups" dataUrlShow="/wastegroup/show" dataUrlEdit="/wastegroup/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />