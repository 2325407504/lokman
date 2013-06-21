<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productgroupList" value="/productgroup/list" />
<spring:url var="productgroupNew" value="/productgroup/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${productgroupList}"><spring:message code="Product Groups"></spring:message></a></li>
	<li class=""><a href="${productgroupNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/productgroup/get" id="productgroups" dataUrlShow="/productgroup/show" dataUrlEdit="/productgroup/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>