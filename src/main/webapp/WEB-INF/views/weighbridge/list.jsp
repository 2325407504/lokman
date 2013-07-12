<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Weighbridges" />
    <jsp:param name="property" value="weighbridge" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/weighbridge/get" id="weighbridges" dataUrlShow="/weighbridge/show" dataUrlEdit="/weighbridge/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.employee.fullname"/>
    <aripd:datatablescolumn label="Clerk" field="clerk"/>
    <aripd:datatablescolumn label="Plate" field="plate"/>
    <aripd:datatablescolumn label="Driver" field="driver"/>
    <aripd:datatablescolumn label="Location From" field="locationFrom"/>
    <aripd:datatablescolumn label="Location To" field="locationTo"/>
    <aripd:datatablescolumn label="Check-in" field="checkin"/>
    <aripd:datatablescolumn label="Check-out" field="checkout"/>
    <aripd:datatablescolumn label="Good Type" field="goodtype"/>
    <aripd:datatablescolumn label="Customer" field="customer"/>
    <aripd:datatablescolumn label="First Weighing" field="firstWeighing"/>
    <aripd:datatablescolumn label="Last Weighing" field="lastWeighing"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />