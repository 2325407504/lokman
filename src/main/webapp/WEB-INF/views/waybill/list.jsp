<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Waybills" />
    <jsp:param name="property" value="waybill" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/waybill/get" id="waybills" dataUrlShow="/waybill/show" dataUrlEdit="/waybill/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Document No" field="documentNo"/>
    <aripd:column label="Document Date" field="documentDate"/>
    <aripd:column label="Company" field="company"/>
    <aripd:column label="Driver" field="driver"/>
    <aripd:column label="Plate" field="plate"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />