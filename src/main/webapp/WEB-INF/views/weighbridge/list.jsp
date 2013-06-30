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
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.client.fullname"/>
    <aripd:column label="Clerk" field="clerk"/>
    <aripd:column label="Plate" field="plate"/>
    <aripd:column label="Driver" field="driver"/>
    <aripd:column label="Location From" field="locationFrom"/>
    <aripd:column label="Location To" field="locationTo"/>
    <aripd:column label="Check-in" field="checkin"/>
    <aripd:column label="Check-out" field="checkout"/>
    <aripd:column label="Good Type" field="goodtype"/>
    <aripd:column label="Customer" field="customer"/>
    <aripd:column label="First Weighing" field="firstWeighing"/>
    <aripd:column label="Last Weighing" field="lastWeighing"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />