<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="customerList" value="/customer/list" />
<spring:url var="customerNew" value="/customer/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${customerList}"><spring:message code="Customers"></spring:message></a></li>
	<li class=""><a href="${customerNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/customer/get" id="customers" dataUrlShow="/customer/show" dataUrlEdit="/customer/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Region" field="region.name"/>
	<aripd:column label="Code" field="code"/>
	<aripd:column label="Fullname" field="name"/>
	<aripd:column label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>