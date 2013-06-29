<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Machines" />
    <jsp:param name="property" value="machine" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/machine/get" id="machines" dataUrlShow="/machine/show" dataUrlEdit="/machine/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />