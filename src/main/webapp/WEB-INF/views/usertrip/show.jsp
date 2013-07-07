<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="usertrip" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="trip">
            <aripd:descriptionitem label="Truck" field="usertripAttribute.truck.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="usertripAttribute.driver.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Point" field="usertripAttribute.startingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Km" field="usertripAttribute.startingKm"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="usertripAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="usertripAttribute.endingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Km" field="usertripAttribute.endingKm"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="usertripAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="usertripAttribute.loadWeightInTonne"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="usertripAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />