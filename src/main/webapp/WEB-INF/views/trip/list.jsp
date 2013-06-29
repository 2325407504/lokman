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
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Truck" field="truck.plate"/>
    <aripd:column label="Driver" field="driver.name"/>
    <aripd:column label="Starting Point" field="startingPoint"/>
    <aripd:column label="Starting Time" field="startingTime"/>
    <aripd:column label="Starting Km" field="startingKm"/>
    <aripd:column label="Ending Point" field="endingPoint"/>
    <aripd:column label="Ending Time" field="endingTime"/>
    <aripd:column label="Ending Km" field="endingKm"/>
    <aripd:column label="Weight" field="loadWeightInTonne"/>
    <aripd:column label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />