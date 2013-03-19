<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="driverList" value="/driver/list" />
<spring:url var="driverNew" value="/driver/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${driverList}"><spring:message code="Drivers"></spring:message></a></li>
	<li class=""><a href="${driverNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/driver/get" id="drivers" dataUrlShow="/driver/show" dataUrlEdit="/driver/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Fullname" field="name"/>
	<aripd:column label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>