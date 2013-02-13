<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Accounts"></spring:message></li>
	<spring:url var="addUrl" value="/account/new" />
	<li class="pull-right">
		<a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
	</li>
</ul>

<aripd:datatables datasource="/account/get" id="accounts" dataUrlShow="/account/show" dataUrlEdit="/account/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Username" field="username"/>
	<aripd:column label="Fullname" field="name"/>
	<aripd:column label="E-mail Address" field="email"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>