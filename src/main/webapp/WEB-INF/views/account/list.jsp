<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Accounts" />
    <jsp:param name="property" value="account" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/account/get" id="accounts" dataUrlShow="/account/show" dataUrlEdit="/account/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Region" field="region.name"/>
	<aripd:column label="Username" field="username"/>
	<aripd:column label="Fullname" field="client.fullname"/>
	<aripd:column label="E-mail Address" field="email"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />