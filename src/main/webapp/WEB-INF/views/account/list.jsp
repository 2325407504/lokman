<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url value="/" var="homeUrl" />
<spring:url var="addUrl" value="/account/new" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Accounts" /></li>
	<li class="pull-right">
		<a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry" /></a>
	</li>
</ul>

<aripd:datatables datasource="/account/get" id="accounts" dataUrlShow="/account/show" dataUrlEdit="/account/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Region" field="region.name"/>
	<aripd:column label="Username" field="username"/>
	<aripd:column label="Fullname" field="client.fullname"/>
	<aripd:column label="E-mail Address" field="email"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />