<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Shifts" />
    <jsp:param name="property" value="shift" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/shift/get" id="shifts" dataUrlShow="/shift/show" dataUrlEdit="/shift/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />