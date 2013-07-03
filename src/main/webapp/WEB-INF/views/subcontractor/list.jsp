<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Subcontractors" />
    <jsp:param name="property" value="subcontractor" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/subcontractor/get" id="subcontractors" dataUrlShow="/subcontractor/show" dataUrlEdit="/subcontractor/edit">
	<aripd:datatablescolumn label="Action" field="id"/>
	<aripd:datatablescolumn label="Region" field="region.name"/>
	<aripd:datatablescolumn label="Code" field="code"/>
	<aripd:datatablescolumn label="Subcontractor" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />