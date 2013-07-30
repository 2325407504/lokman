<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Waybills" />
    <jsp:param name="property" value="waybill" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/waybill/get" id="waybills" dataUrlShow="/waybill/show" dataUrlEdit="/waybill/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Member" field="member.username"/>
    <aripd:datatablescolumn label="Document No" field="documentNo"/>
    <aripd:datatablescolumn label="Document Date" field="documentDate"/>
    <aripd:datatablescolumn label="Company" field="company"/>
    <aripd:datatablescolumn label="Driver" field="driver"/>
    <aripd:datatablescolumn label="Plate" field="plate"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />