<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expensetypeList" value="/expensetype/list" />
<spring:url var="expensetypeNew" value="/expensetype/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${expensetypeList}"><spring:message code="Expensetypes" /></a></li>
	<li class=""><a href="${expensetypeNew}"><spring:message code="New Entry" /></a></li>
</ul>

<aripd:datatables datasource="/expensetype/get" id="expensetypes" dataUrlShow="/expensetype/show" dataUrlEdit="/expensetype/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>