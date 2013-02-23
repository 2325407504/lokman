<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="addUrl" value="/role/new"/>

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Roles"></spring:message></li>
	<li class="pull-right">
		<a class="btn btn-mini" href="${addUrl}"><spring:message code="New Entry"></spring:message></a>
	</li>
</ul>

<aripd:datatables datasource="/role/get" id="roles" dataUrlShow="/role/show" dataUrlEdit="/role/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Code" field="code"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>