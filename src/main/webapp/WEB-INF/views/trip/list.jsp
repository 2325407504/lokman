<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="trip" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/trip/get" id="trips" dataUrlShow="/trip/show" dataUrlEdit="/trip/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.username"/>
    <aripd:datatablescolumn label="Truck" field="truck.plate"/>
    <aripd:datatablescolumn label="Driver" field="driver.name"/>
    <aripd:datatablescolumn label="Starting Point" field="startingPoint"/>
    <aripd:datatablescolumn label="Starting Time" field="startingTime"/>
    <aripd:datatablescolumn label="Starting Km" field="startingKm"/>
    <aripd:datatablescolumn label="Ending Point" field="endingPoint"/>
    <aripd:datatablescolumn label="Ending Time" field="endingTime"/>
    <aripd:datatablescolumn label="Ending Km" field="endingKm"/>
    <aripd:datatablescolumn label="Weight" field="loadWeightInTonne"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />