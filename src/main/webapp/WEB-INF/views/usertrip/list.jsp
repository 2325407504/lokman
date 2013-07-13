<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="usertrip" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/usertrip/get" id="trips" dataUrlShow="/usertrip/show" dataUrlEdit="/usertrip/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Account" field="account.username"/>
    <aripd:datatablescolumn label="Truck" field="truck.plate"/>
    <aripd:datatablescolumn label="Driver" field="driver.name"/>
    <aripd:datatablescolumn label="Starting Point" field="startingpoint"/>
    <aripd:datatablescolumn label="Starting Time" field="startingTime"/>
    <aripd:datatablescolumn label="Starting Km" field="startingKm"/>
    <aripd:datatablescolumn label="Ending Point" field="endingpoint"/>
    <aripd:datatablescolumn label="Ending Time" field="endingTime"/>
    <aripd:datatablescolumn label="Ending Km" field="endingKm"/>
    <aripd:datatablescolumn label="Weight" field="weight"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />