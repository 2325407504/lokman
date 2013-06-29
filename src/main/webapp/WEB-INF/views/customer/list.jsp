<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Customers" />
    <jsp:param name="property" value="customer" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/customer/get" id="customers" dataUrlShow="/customer/show" dataUrlEdit="/customer/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Tax No" field="taxNo"/>
    <aripd:column label="Fullname" field="name"/>
    <aripd:column label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />